package com.gestaodestock.gestaodestock.ExeccoesController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problema {
    private Integer estado;
    private LocalDateTime dataEhora;
    private  String titlo;
    private  String tipo;
    private  String detalhes;

}
