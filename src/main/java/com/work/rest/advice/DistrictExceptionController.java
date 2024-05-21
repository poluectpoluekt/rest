package com.work.rest.advice;

import com.work.rest.exception.DistrictAlreadyExistException;
import com.work.rest.exception.DistrictNotFoundException;
import com.work.rest.exception.response.DistrictResponse;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DistrictExceptionController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    private DistrictResponse error(RuntimeException e){
        return new DistrictResponse(e.getMessage(), System.currentTimeMillis());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DistrictAlreadyExistException.class)
    private DistrictResponse districtAlreadyRegistred(DistrictAlreadyExistException e){
        return new DistrictResponse(e.getMessage(), System.currentTimeMillis());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DistrictNotFoundException.class)
    private DistrictResponse districtNotFound(DistrictNotFoundException e){
        return new DistrictResponse(e.getMessage(), System.currentTimeMillis());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private Map<String, String> error(MethodArgumentNotValidException exception){
        Map<String,String> errorValid = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(
                errors -> {
                    String field = ((FieldError)errors).getField();
                    String errorMessage = errors.getDefaultMessage();
                    errorValid.put(field, errorMessage);
                }
        );
        return errorValid;
    }
}
