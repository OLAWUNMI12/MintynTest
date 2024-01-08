package com.test.mintyn.exception;

public class EmptyBinException extends RuntimeException {

    private String message;

    public EmptyBinException() {
    }

    public EmptyBinException(String msg) {
        super(msg);
        this.message = msg;
    }
}
