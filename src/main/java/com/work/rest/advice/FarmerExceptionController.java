package com.work.rest.advice;


import com.work.rest.exception.FarmerAlreadyExistException;
import com.work.rest.exception.FarmerNotFoundException;
import com.work.rest.exception.response.FarmerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FarmerExceptionController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    private FarmerResponse error(RuntimeException e){
        return new FarmerResponse(e.getMessage(), System.currentTimeMillis());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FarmerAlreadyExistException.class)
    private FarmerResponse districtAlreadyRegistred(FarmerAlreadyExistException e){
        String messageException = String.format("This \"%s\" already exists.", e.getMessage());
        return new FarmerResponse(messageException, System.currentTimeMillis());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FarmerNotFoundException.class)
    private FarmerResponse districtNotFound(FarmerNotFoundException e){
        String messageException = String.format("Farmer with the \"%s\" was not found", e.getMessage());
        return new FarmerResponse(messageException, System.currentTimeMillis());
    }
}
