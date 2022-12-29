package com.cruisecompany.service;

import com.cruisecompany.dao.db.DBProvider;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.dto.mapper.DTOMapper;
import com.cruisecompany.entity.*;
import com.cruisecompany.exception.EmailAlreadyExistsException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.util.files.FileHelper;
import com.cruisecompany.util.files.FileType;
import com.cruisecompany.util.validator.Validators;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.http.Part;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TicketServiceTest {
    static Connection connection = null;
    static DBProvider dbProvider;
    static TicketService ticketService;
    static CruiseService cruiseService;
    static ShipService shipService;
    static UserAccountService userAccountService;
    static Cruise cruise;
    static Cruise cruiseExpensive;
    static Passenger passenger;
    static PassengerDTO passengerDTO;
    static long orderId;

    static void setupConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(TicketServiceTest.class.getClassLoader().getResourceAsStream("app.properties"));
        String username = properties.getProperty("database.username");
        String password = properties.getProperty("database.password");
        String url = properties.getProperty("database.url");
        connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
    }

    static void setupDatabase() throws ServiceException, IOException, EmailAlreadyExistsException {
        try (MockedStatic<FileHelper> mocked = mockStatic(FileHelper.class);
             MockedStatic<Validators> mocked2 = mockStatic(Validators.class)) {
            Ship ship = Ship.builder()
                    .photoPath("test")
                    .name("test")
                    .passengerCapacity(10)
                    .build();
            Part part = Mockito.mock(Part.class);
            mocked.when(() -> FileHelper.writeRecord(ArgumentMatchers.any(Part.class),
                    ArgumentMatchers.anyString(),
                    ArgumentMatchers.any(FileType.class),
                    ArgumentMatchers.anyString())).thenReturn("test");
            shipService.addShip(ship, part, "");
            cruise = Cruise.builder()
                    .dateArrival(LocalDate.now())
                    .dateDeparture(LocalDate.now())
                    .ship(ship)
                    .price(BigDecimal.valueOf(1))
                    .stationList(new ArrayList<>())
                    .timeDeparture(LocalTime.parse("21:00"))
                    .build();
            cruiseExpensive = Cruise.builder()
                    .dateArrival(LocalDate.now())
                    .dateDeparture(LocalDate.now())
                    .ship(ship)
                    .price(BigDecimal.valueOf(1000))
                    .stationList(new ArrayList<>())
                    .timeDeparture(LocalTime.parse("21:00"))
                    .build();

            cruiseService.addCruise(cruise);
            cruiseService.addCruise(cruiseExpensive);

            UserAccount userAccount = UserAccount.builder()
                    .email("test")
                    .password("test")
                    .role("USER")
                    .build();

            passenger = Passenger.builder()
                    .userAccount(userAccount)
                    .firstName("test")
                    .lastName("test")
                    .money(BigDecimal.valueOf(10))
                    .build();
            userAccountService.signUp(passenger);
            passengerDTO = DTOMapper.toPassengerDTO(passenger);
        }
    }

    @BeforeAll
    static void setUp() throws EmailAlreadyExistsException {
        try {
            setupConnection();
            dbProvider = mock(DBProvider.class);
            when(dbProvider.getConnection()).thenReturn(connection);

            ServiceFactory serviceFactory = new ServiceFactory(dbProvider);
            ticketService = serviceFactory.getOrderService();
            cruiseService = serviceFactory.getCruiseService();
            shipService = serviceFactory.getShipService();
            userAccountService = serviceFactory.getUserAccountService();
            setupDatabase();
            clearInvocations(dbProvider);
        } catch (SQLException | IOException | ServiceException ex) {
            fail(ex);
        }
    }

    @AfterAll
    static void tearDown() throws SQLException {
        if (connection == null) fail();
        connection.rollback();
        connection.close();
    }

    @Test
    @Order(5)
    void testGetAllPassengerOrders() throws ServiceException {
        List<Ticket> list = ticketService.getAllPassengerOrders(passenger.getId());
        verify(dbProvider,times(4)).commit(connection);
        assertNotNull(list);
    }

    @Test
    @Order(6)
    void testPay() throws ServiceException {
        BigDecimal moneyLeft = ticketService.pay(orderId);
        verify(dbProvider,times(5)).commit(connection);
        assertTrue(moneyLeft.longValue()>=0);
    }

    @Test
    @Order(1)
    void testBuy() throws ServiceException, SQLException {
        orderId = ticketService.buy(passengerDTO, cruise.getId());
        verify(dbProvider, times(1)).commit(connection);
    }

    @Test
    @Order(2)
    void testBlock() throws ServiceException {
        ticketService.block(orderId);
        verify(dbProvider,times(2)).commit(connection);
    }

    @Test
    @Order(3)
    void testUnblock() throws ServiceException {
        ticketService.unblock(orderId);
        verify(dbProvider,times(3)).commit(connection);
    }

    @Test
    @Order(4)
    void testConfirm() throws ServiceException {
        ticketService.confirm(orderId);
        verify(dbProvider,times(4)).commit(connection);
    }

    @Test
    void testNotEnoughMoney() throws SQLException, ServiceException, IOException, EmailAlreadyExistsException {
        connection.rollback();
        setupDatabase();
        passenger.setMoney(BigDecimal.valueOf(0));
        orderId = ticketService.buy(passengerDTO, cruiseExpensive.getId());
        clearInvocations(dbProvider);
        assertThrows(ServiceException.class,()-> ticketService.pay(orderId));
        verify(dbProvider, times(0)).commit(connection);
    }
}