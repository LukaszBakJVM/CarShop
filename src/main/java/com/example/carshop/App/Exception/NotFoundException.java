package com.example.carshop.App.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "NOT_FOUND")
public class NotFoundException extends  RuntimeException{
    public NotFoundException() {
    }
}
