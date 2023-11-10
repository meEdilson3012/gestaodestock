package com.gestaodestock.gestaodestock.domain.DTOs;

import com.gestaodestock.gestaodestock.domain.Model.Relatorio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Relatorio_DTO (Long id, String produto, LocalDateTime data, String tipo, Integer quantidade, BigDecimal precoTotal,String descricao) {

    public Relatorio_DTO (Relatorio relatorio){
        this(relatorio.getId(), relatorio.getProduto(), relatorio.getData(), relatorio.getTipo(), relatorio.getQuantidade(),
                relatorio.getPrecoTotal(), relatorio.getDescricao());
    }
}
