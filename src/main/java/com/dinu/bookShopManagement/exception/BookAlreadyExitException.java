package com.dinu.bookShopManagement.exception;

public class BookAlreadyExitException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BookAlreadyExitException(String message) {
        super(message);
    }
}
