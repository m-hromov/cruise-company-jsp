package com.cruisecompany.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class CurrencyTag extends TagSupport {
    final static Logger logger = LogManager.getLogger(CurrencyTag.class);
    private BigDecimal value;
    private String initialcurr;
    private String convertedcurr;
    private HashMap<String, Double> currencyMap;
    private HashMap<String, String> currencySymbolMap;
    private JspWriter jspWriter;

    public CurrencyTag() {
        initialcurr = "USD";
        convertedcurr = "USD";
        currencyMap = new HashMap<>();
        currencyMap.put("USD:UAH", 37d);
        currencyMap.put("UAH:USD", 0.027);
        currencyMap.put("USD:USD", 1d);
        currencySymbolMap = new HashMap<>();
        currencySymbolMap.put("UAH", "₴");
        currencySymbolMap.put("USD", "$");
    }

    protected void setJspWriter(JspWriter jspWriter) {
        this.jspWriter = jspWriter;
    }

    @Override
    public int doStartTag() throws JspException {
        if (jspWriter == null) jspWriter = pageContext.getOut();
        BigDecimal convertedValue = value.multiply(BigDecimal.valueOf(getMultiplier()));
        String symbol = getSymbol();
        try {
            jspWriter.println(convertedValue.setScale(0, RoundingMode.DOWN) + symbol);
        } catch (IOException e) {
            logger.error("Unable do covert money with tag!", e);
            throw new JspException(e);
        } finally {
            jspWriter = null;
        }

        return SKIP_BODY;
    }

    protected double getMultiplier() {
        return currencyMap.get(initialcurr + ":" + convertedcurr);
    }

    protected String getSymbol() {
        return currencySymbolMap.get(convertedcurr);
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getInitialcurr() {
        return initialcurr;
    }

    public void setInitialcurr(String initialcurr) {
        this.initialcurr = initialcurr;
    }

    public String getConvertedcurr() {
        return convertedcurr;
    }

    public void setConvertedcurr(String convertedcurr) {
        this.convertedcurr = convertedcurr;
    }
}
