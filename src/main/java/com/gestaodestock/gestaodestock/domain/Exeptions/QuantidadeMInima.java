package com.gestaodestock.gestaodestock.domain.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class QuantidadeMInima extends  RuntimeException{

    public  QuantidadeMInima(String msg){
        super(msg);
    }
}
