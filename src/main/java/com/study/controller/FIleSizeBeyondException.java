package com.study.controller;

public class FIleSizeBeyondException extends RuntimeException {
    public FIleSizeBeyondException() {
        super();
    }

    public FIleSizeBeyondException(String message) {
        super(message);
    }

    public FIleSizeBeyondException(String message, Throwable cause) {
        super(message, cause);
    }

    public FIleSizeBeyondException(Throwable cause) {
        super(cause);
    }

    protected FIleSizeBeyondException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
