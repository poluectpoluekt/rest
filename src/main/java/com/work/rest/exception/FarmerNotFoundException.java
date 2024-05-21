package com.work.rest.exception;

public class FarmerNotFoundException extends RuntimeException{

    public FarmerNotFoundException(String message){
        super(message);
    }
}
