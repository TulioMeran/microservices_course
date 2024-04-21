package com.tuliomeran.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNoFoundException extends RuntimeException{
    public ResourceNoFoundException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s not found with the given input data %s : %s", resourceName, fieldName, fieldValue));
    }
}
