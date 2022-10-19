package com.cruisecompany.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ControllerTest {
    Controller controller = new Controller();
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    HttpServletMapping mapping;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testProcessRequest() {
        doNothing().when(session).invalidate();
        when(request.getSession()).thenReturn(session);
        when(request.getHttpServletMapping()).thenReturn(mapping);
        when(mapping.getMatchValue()).thenReturn("do_sign_out");
        String path = controller.processRequest(request, response);
        verify(session, times(1)).invalidate();
        assertEquals("/", path);
    }

    @Test
    void testErrorProcessRequest() {
        doNothing().when(session).invalidate();
        when(request.getHttpServletMapping()).thenReturn(mapping);
        when(mapping.getMatchValue()).thenReturn("test_error_mapping123321");
        String path = controller.processRequest(request, response);
        assertEquals("/WEB-INF/view/error.jsp", path);
    }
}