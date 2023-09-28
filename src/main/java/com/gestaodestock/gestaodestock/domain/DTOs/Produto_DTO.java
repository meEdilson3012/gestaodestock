package com.gestaodestock.gestaodestock.domain.DTOs;


import com.gestaodestock.gestaodestock.domain.Model.Produto;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Produto_DTO {
    private Long Id;

    private String nome;

    private String descricao;

    private String categoria;

    private String  fornecedor;

    private BigDecimal precoCompra;

    private BigDecimal precoVenda;

    private Integer quanidadeMin;

    private Integer quantidadeMax;

    private Integer quantidadeActual;
    public Produto_DTO(Produto produto) {
        this.Id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.categoria = produto.getCategoria();
        this.fornecedor = produto.getFornecedor();
        this.precoCompra = produto.getPrecoCompra();
        this.precoVenda = produto.getPrecoVenda();
        this.quanidadeMin = produto.getQuanidadeMin();
        this.quantidadeMax = produto.getQuanidadeMin();
        this.quantidadeActual= produto.getQuantidadeActual();
    }

    public Produto_DTO (){
        super();
    }
}
