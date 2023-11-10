package com.gestaodestock.gestaodestock.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class Entrada_DTO{
    Long id;
    @NotNull(message = "O valor da quantidade não deve ser nula")
    @Min(value = 1,message = "A quantidade deve ser maior ou igual a zero")
    int quantidade;
    @Min(value = 0,message = "O numero de lote deve ser maior ou igual a zero")
    int numeroLote;

    @NotNull
    Produto produto;
    public  Entrada_DTO (Entrada entrada){
       this.id= entrada.getId();
       this.quantidade= entrada.getQuantidade();
       this.produto= entrada.getProduto();
    }
    public  Entrada_DTO(){
        super();
    }
}
