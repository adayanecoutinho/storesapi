package com.coutinho.adayane.storesapi.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LojaNotFoundException extends RuntimeException {

    public LojaNotFoundException(String msg) {
        super(msg);
    }
}
