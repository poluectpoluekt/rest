package com.work.rest.exception;

public class FarmerNotFoundException extends RuntimeException{

    public FarmerNotFoundException(String id){
        super(id);
    }
}
