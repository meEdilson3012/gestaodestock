package com.gestaodestock.gestaodestock.domain.DTOs;

import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Model.Saida;

import java.time.LocalDateTime;

public record Saida_Listar_DTO (Long id, int quantidade, LocalDateTime dataSaida, String motivoRetirada, Produto produto){

    public Saida_Listar_DTO (Saida saida){
        this(saida.getId(), saida.getQuantidade(), saida.getDataSaida(),
                saida.getMotivoRetirada(), saida.getProduto());
    }
}
