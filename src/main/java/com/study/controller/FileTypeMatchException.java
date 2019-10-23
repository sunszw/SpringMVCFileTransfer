package com.study.controller;

public class FileTypeMatchException extends RuntimeException {
    public FileTypeMatchException() {
        super();
    }

    public FileTypeMatchException(String message) {
        super(message);
    }

    public FileTypeMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeMatchException(Throwable cause) {
        super(cause);
    }

    protected FileTypeMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
