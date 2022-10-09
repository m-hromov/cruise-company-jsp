package com.cruisecompany.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class CurrencyTag extends TagSupport {
    private BigDecimal value;
    private String initialcurr;
    private String convertedcurr;
    private HashMap<String,Double> currencyMap;
    private HashMap<String,String> currencySymbolMap;
    public CurrencyTag() {
        initialcurr = "USD";
        convertedcurr = "USD";
        currencyMap = new HashMap<>();
        currencyMap.put("USD:UAH",37d);
        currencyMap.put("UAH:USD",0.027);
        currencyMap.put("USD:USD",1d);
        currencySymbolMap = new HashMap<>();
        currencySymbolMap.put("UAH", "₴");
        currencySymbolMap.put("USD", "$");
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter jspWriter = pageContext.getOut();
        BigDecimal convertedValue = value.multiply(BigDecimal.valueOf(getMultiplier()));
        String symbol = getSymbol();
        try {
            jspWriter.println(convertedValue + symbol);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    protected double getMultiplier(){
        return currencyMap.get(initialcurr + ":" + convertedcurr);
    }
    protected String getSymbol(){
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
