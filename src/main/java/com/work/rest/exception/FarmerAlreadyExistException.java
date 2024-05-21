package com.work.rest.exception;

public class FarmerAlreadyExistException extends RuntimeException {

    public FarmerAlreadyExistException(String message){
        super(message);
    }
}
