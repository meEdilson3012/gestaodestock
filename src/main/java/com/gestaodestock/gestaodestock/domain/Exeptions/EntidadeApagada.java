package com.gestaodestock.gestaodestock.domain.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeApagada extends  RuntimeException{

    public  EntidadeApagada(String Msg){
        super(Msg);
    }
}
