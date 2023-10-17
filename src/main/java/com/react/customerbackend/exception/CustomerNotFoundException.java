package com.react.customerbackend.exception;

public class CustomerNotFoundException extends RuntimeException{

    private String message;
    public CustomerNotFoundException(String message){
        super(message);
    }
}
