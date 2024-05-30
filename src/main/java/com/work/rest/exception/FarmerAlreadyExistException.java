package com.work.rest.exception;

import org.apache.logging.log4j.message.StringFormattedMessage;

public class FarmerAlreadyExistException extends RuntimeException {

    //private final static String message = String.format("This \"%s\" already exists.");

    public FarmerAlreadyExistException(String title){
        super(String.format("This \"%s\" already exists.", title));
    }
}
