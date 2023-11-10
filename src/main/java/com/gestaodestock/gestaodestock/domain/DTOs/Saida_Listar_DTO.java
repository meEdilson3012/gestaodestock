package com.gestaodestock.gestaodestock.domain.DTOs;

import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Model.Saida;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Saida_Listar_DTO(Long id, int quantidade, LocalDateTime dataSaida, String motivoRetirada, BigDecimal custo, Produto produto){

    public Saida_Listar_DTO(Saida saida){
        this(saida.getId(),saida.getQuantidade(),saida.getDataSaida()
        ,saida.getMotivoRetirada(),saida.getCusto(),saida.getProduto());
    }
}

/*public class Saida_Listar_DTO {

    Long id;
    int quantidade;
    LocalDateTime dataSaida;
    String motivoRetirada;
    Produto_Listar_DTO produto_listar_dto;
    public Saida_Listar_DTO (Saida saida){
        this.id= saida.getId();
        this.quantidade= saida.getQuantidade();
        this.dataSaida= saida.getDataSaida();
        this.motivoRetirada= saida.getMotivoRetirada();
        this.produto_listar_dto=saidaDTO(saida.getProduto());
    }

    public Produto_Listar_DTO saidaDTO(Produto produto){
        Produto_Listar_DTO produto_listar_dto1 = new Produto_Listar_DTO(produto);
        return  produto_listar_dto1;
    }
}

 */
