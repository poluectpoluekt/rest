package com.work.rest.exception;

import org.apache.logging.log4j.message.StringFormattedMessage;

public class FarmerAlreadyExistException extends RuntimeException {

    public FarmerAlreadyExistException(String title){
        super(title);
    }
}
