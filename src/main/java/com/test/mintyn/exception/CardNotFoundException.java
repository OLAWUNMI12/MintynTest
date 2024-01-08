package com.test.mintyn.exception;

public class CardNotFoundException extends RuntimeException {

    private String message;

    public CardNotFoundException() {
    }

    public CardNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
