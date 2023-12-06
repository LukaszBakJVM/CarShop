package com.example.carshop.App.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = ("Brak części o takim numerze"))
public class NotFoundException extends  RuntimeException{
}
