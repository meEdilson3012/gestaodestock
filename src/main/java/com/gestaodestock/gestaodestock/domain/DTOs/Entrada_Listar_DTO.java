package com.gestaodestock.gestaodestock.domain.DTOs;

import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Entrada_Listar_DTO(Long id, int quantidade, LocalDateTime dataEntrada, int numeroLote, BigDecimal custoUnitorio, Produto produto) {

    public  Entrada_Listar_DTO (Entrada entrada){
        this(entrada.getId(),entrada.getQuantidade(),entrada.getDataEntrada(),
                entrada.getNumeroLote(), entrada.getCusto(),entrada.getProduto() );
    }

}
