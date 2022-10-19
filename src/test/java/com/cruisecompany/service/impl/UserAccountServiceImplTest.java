package com.cruisecompany.service.impl;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dto.UserAccountDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

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
        userAccount = new UserAccount();
        userAccount.setLogin("test@gmail.com")
                .setPassword("testpassword")
                .setRole("USER");
        passenger = new Passenger();
        passenger.setFirstName("Test1")
                .setLastName("Test2")
                .setPhone("Test3")
                .setEmail("test@gmail.com")
                .setMoney(BigDecimal.valueOf(100))
                .setDocumentPath("Test4")
                .setUserAccount(userAccount);
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
    @Order(2)
    void testSignIn() throws ServiceException, SQLException {
        UserAccountDTO uadto = userAccountService.signIn("test@gmail.com", "testpassword").get();
        assertEquals(userAccount.getLogin(), uadto.getLogin());
        assertEquals(userAccount.getRole(), uadto.getRole());
    }

    @Test
    @Order(1)
    void testSignUp() {
        assertDoesNotThrow(() -> userAccountService.signUp(passenger));
    }
}