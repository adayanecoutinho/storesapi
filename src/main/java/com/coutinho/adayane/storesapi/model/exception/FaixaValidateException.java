package com.coutinho.adayane.storesapi.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FaixaValidateException extends RuntimeException{

    public FaixaValidateException(String msg){
        super(msg);
    }
}
