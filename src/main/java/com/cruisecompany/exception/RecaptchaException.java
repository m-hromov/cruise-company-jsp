package com.cruisecompany.exception;

public class RecaptchaException extends Exception{
    public RecaptchaException() {
        super();
    }

    public RecaptchaException(String message) {
        super(message);
    }

    public RecaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecaptchaException(Throwable cause) {
        super(cause);
    }

    protected RecaptchaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
