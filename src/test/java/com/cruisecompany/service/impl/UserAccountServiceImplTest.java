package com.cruisecompany.service.impl;

import com.cruisecompany.dao.db.DBProvider;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.AuthorizationException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;
import com.cruisecompany.util.validator.Validators;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserAccountServiceImplTest {

    static Connection connection = null;
    static UserAccountService userAccountService;
    static Passenger passenger;
    static UserAccount userAccount;
    static DBProvider dbProvider;

    @BeforeAll
    static void setUp() {
        try {
            setupConnection();

            dbProvider = Mockito.mock(DBProvider.class);
            Mockito.when(dbProvider.getConnection()).thenReturn(connection);

            ServiceFactory serviceFactory = new ServiceFactory(dbProvider);
            userAccountService = serviceFactory.getUserAccountService();
        } catch (SQLException | IOException ex) {
            fail(ex);
        }
        userAccount = UserAccount.builder()
                .email("test@gmail.com")
                .password("testpassword")
                .role("USER")
                .build();
        passenger = Passenger.builder()
                .firstName("Test1")
                .lastName("Test2")
                .phone("Test3")
                .money(BigDecimal.valueOf(100))
                .documentPath("Test4")
                .userAccount(userAccount)
                .build();
    }

    @AfterAll
    static void tearDown() throws SQLException {
        if (connection == null) fail();
        connection.rollback();
        connection.close();
    }

    static void setupConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(UserAccountServiceImplTest.class.getClassLoader().getResourceAsStream("app.properties"));
        String username = properties.getProperty("database.username");
        String password = properties.getProperty("database.password");
        String url = properties.getProperty("database.url");
        connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
    }



    @Test
    @Order(1)
    void testSignUp() {
        try (MockedStatic<Validators> mocked = mockStatic(Validators.class)){
            assertDoesNotThrow(() -> userAccountService.signUp(passenger));
        }
    }
    @Test
    @Order(2)
    void testSignIn() throws ServiceException, AuthorizationException, SQLException {
        try (MockedStatic<Validators> mocked = mockStatic(Validators.class)){
            PassengerDTO pdto = userAccountService.signIn("test@gmail.com", "testpassword").get();
            assertEquals(userAccount.getEmail(), pdto.getEmail());
            assertEquals(userAccount.getRole(), pdto.getRole());
        }
    }
}