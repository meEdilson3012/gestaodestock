package com.gestaodestock.gestaodestock.domain.DTOs;

import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class Entrada_DTO{
    Long id; int quantidade;
    LocalDateTime dataEntrada;
    int numeroLote;
    BigDecimal custoUnitorio;
    Produto produto;
    public  Entrada_DTO (Entrada entrada){
       this.id= entrada.getId();
       this.quantidade= entrada.getQuantidade();
       this.dataEntrada= entrada.getDataEntrada();
       this.numeroLote= entrada.getNumeroLote();
       this.custoUnitorio= entrada.getCustoUnitorio();
       this.produto= entrada.getProduto();
    }
    public  Entrada_DTO(){
        super();
    }
}
