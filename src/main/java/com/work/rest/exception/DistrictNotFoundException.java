package com.work.rest.exception;

public class DistrictNotFoundException extends RuntimeException {

    public DistrictNotFoundException(String title){
        super(title);
    }
}
