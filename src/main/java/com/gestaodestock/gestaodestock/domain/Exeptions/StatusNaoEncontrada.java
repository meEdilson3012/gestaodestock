package com.gestaodestock.gestaodestock.domain.Exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class StatusNaoEncontrada extends EntidadeNaoEncontrada {

    public StatusNaoEncontrada(String mensagem) {
        super(mensagem);
    }
}
