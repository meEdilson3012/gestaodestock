package com.gestaodestock.gestaodestock.domain.DTOs;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;

//@Getter
public record Produto_Listar_DTO (Long id, String nome, String descricao, String categoria, String fornecedor, BigDecimal precoCompra, BigDecimal precoVenda, int quanidadeMin, int quantidadeMax, int quantidadeActual) {

     public Produto_Listar_DTO(Produto produto) {
         this(produto.getId(), produto.getNome(), produto.getDescricao(),
                 produto.getCategoria(), produto.getFornecedor()
                 , produto.getPrecoCompra(), produto.getPrecoVenda(),
                 produto.getQuanidadeMin(), produto.getQuantidadeMax(), produto.getQuantidadeActual());
     }


}
