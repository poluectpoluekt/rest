package com.work.rest.exception;

public class DistrictAlreadyExistException extends RuntimeException{

    public DistrictAlreadyExistException(String message){
        super(message);
    }
}
