package com.rishiqing.qywx.webalert.service.exception;

public class AlertException extends RuntimeException {
    public AlertException() {
        super();
    }

    public AlertException(String message) {
        super(message);
    }

    public AlertException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlertException(Throwable cause) {
        super(cause);
    }

    protected AlertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
