package com.cruisecompany.tag;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
class CurrencyTagTest {
    @Mock
    JspWriter jspWriter;
    String out;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() throws IOException {
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.doAnswer(invocationOnMock -> out = invocationOnMock.getArgument(0))
                .when(jspWriter).println(ArgumentMatchers.anyString());
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void testDoStartTag() throws JspException {
        CurrencyTag currencyTag = new CurrencyTag();
        currencyTag.setJspWriter(jspWriter);
        currencyTag.setValue(BigDecimal.valueOf(100));
        currencyTag.setInitialcurr("USD");
        currencyTag.setConvertedcurr("UAH");
        currencyTag.doStartTag();
        assertEquals("3700₴",out);
        currencyTag.setConvertedcurr("USD");
        currencyTag.setJspWriter(jspWriter);
        currencyTag.doStartTag();
    }
    @Test
    void testCurrencyMultiplier() throws JspException {
        CurrencyTag currencyTag = new CurrencyTag();
        currencyTag.setJspWriter(jspWriter);
        currencyTag.setValue(BigDecimal.valueOf(10));
        currencyTag.setInitialcurr("USD");
        currencyTag.setConvertedcurr("USD");
        currencyTag.doStartTag();
        assertEquals(currencyTag.getMultiplier(),1);
    }
    @Test
    void testCurrencySymbol() throws JspException {
        CurrencyTag currencyTag = new CurrencyTag();
        currencyTag.setJspWriter(jspWriter);
        currencyTag.setValue(BigDecimal.valueOf(10));
        currencyTag.setInitialcurr("USD");
        currencyTag.setConvertedcurr("USD");
        currencyTag.doStartTag();
        assertEquals(currencyTag.getSymbol(),"$");
    }
}