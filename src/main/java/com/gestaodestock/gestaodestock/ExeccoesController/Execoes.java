package com.gestaodestock.gestaodestock.ExeccoesController;

import com.gestaodestock.gestaodestock.domain.Exeptions.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Date;
import java.time.LocalDateTime;

@ControllerAdvice
public class Execoes extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontrada.class)
    public ResponseEntity<?> entidadeNAOencontrada(EntidadeNaoEncontrada e, WebRequest webRequest){

       Problema problema= iserirProblema(HttpStatus.NOT_FOUND,"Entidade Não Encontrada",e,"") ;
       return handleExceptionInternal(e,problema,new HttpHeaders(),HttpStatus.NOT_FOUND,webRequest );
    }

    @ExceptionHandler(EntidadeemUso.class)
    public ResponseEntity<?> entidadeEMusu(EntidadeemUso e, WebRequest webRequest){
        Problema problema= iserirProblema(HttpStatus.CONFLICT,"Entidade Em Usu",e,"") ;
        return handleExceptionInternal(e,problema,new HttpHeaders(),HttpStatus.CONFLICT,webRequest );
    }
    @ExceptionHandler(QuantidadeNaoValida.class)
    public ResponseEntity<?> quantidadeNAOvalida(QuantidadeNaoValida e,WebRequest webRequest){
        Problema problema= iserirProblema(HttpStatus.BAD_REQUEST,"Quantidade Invalida",e,"") ;
        return handleExceptionInternal(e,problema,new HttpHeaders(),HttpStatus.BAD_REQUEST,webRequest );
    }

    @ExceptionHandler(PrecoNaoValido.class)
    public ResponseEntity<?> precoNAOvalido(PrecoNaoValido e,WebRequest webRequest){
        Problema problema= iserirProblema(HttpStatus.BAD_REQUEST,"Preço Invalido",e,"") ;
        return handleExceptionInternal(e,problema,new HttpHeaders(),HttpStatus.BAD_REQUEST,webRequest );
    }
    @ExceptionHandler(QuantidadeMInima.class)
    public ResponseEntity<?> quantidadeMinima(QuantidadeMInima e,WebRequest webRequest) {
        Problema problema= iserirProblema(HttpStatus.CONFLICT,"Quantidade Minima Inavalida",e,"") ;
        return handleExceptionInternal(e,problema,new HttpHeaders(),HttpStatus.CONFLICT,webRequest );
    }
    @ExceptionHandler(EntidadeApagada.class)
    public ResponseEntity<?> entidadeApagada(EntidadeApagada e,WebRequest webRequest) {
        Problema problema= iserirProblema(HttpStatus.CONFLICT,"Entidade Indesponivel",e,"") ;
        return handleExceptionInternal(e,problema,new HttpHeaders(),HttpStatus.CONFLICT,webRequest );
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        HttpStatus status = (HttpStatus) statusCode;
        if (body == null){
            Problema problema = Problema.builder()
                    .estado(status.value())
                    .tipo(status.getReasonPhrase())
                    .build();
        }else if( body instanceof String){
            Problema problema = Problema.builder()
                    .estado(statusCode.value())
                    .dataEhora(LocalDateTime.now())
                    .tipo(status.getReasonPhrase())
                    .detalhes((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Problema problema = iserirProblema(status,"Erro de Sintaxe",null,
                "O corpo da requisição está invalida. Verifique a sintaxe.");
        return  super.handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Problema problema = iserirProblema(status,"Erro de Sintaxe",null,
                ex.getBody().getDetail());
        return  super.handleExceptionInternal(ex, problema, headers, status, request);
    }

    public  Problema iserirProblema(HttpStatusCode httpStatus, String titlo, Exception e, String detalhes){
        HttpStatus status = (HttpStatus) httpStatus;

        Problema problema = Problema.builder()
                .estado(status.value())
                .dataEhora(LocalDateTime.now())
                .titlo(titlo)
                .tipo(status.getReasonPhrase())
                .detalhes((e==null)?detalhes:e.getMessage()).build();
        return  problema;
    }
}
