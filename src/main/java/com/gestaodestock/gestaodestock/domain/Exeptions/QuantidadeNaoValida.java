package com.gestaodestock.gestaodestock.domain.Exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuantidadeNaoValida extends RuntimeException{
    public QuantidadeNaoValida(String mensagem) {
        super   (mensagem);
    }

}
