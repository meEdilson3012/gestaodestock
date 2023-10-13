package com.gestaodestock.gestaodestock.ExeccoesController;

import com.gestaodestock.gestaodestock.domain.Exeptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Execoes {

    @ExceptionHandler(EntidadeNaoEncontrada.class)
    public ResponseEntity<?> entidadeNAOencontrada(EntidadeNaoEncontrada e){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(EntidadeemUso.class)
    public ResponseEntity<?> entidadeEMusu(EntidadeemUso e){
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
    @ExceptionHandler(QuantidadeNaoValida.class)
    public ResponseEntity<?> quantidadeNAOvalida(QuantidadeNaoValida e){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(PrecoNaoValido.class)
    public ResponseEntity<?> precoNAOvalido(PrecoNaoValido e){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
