package com.gestaodestock.gestaodestock.domain.DTOs;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.tomcat.util.bcel.Const;

import java.math.BigDecimal;

@Data
public class Produto_DTO {
    private Long Id;
    @NotNull(message = "O nome do produto não deve ser nula")
    @NotBlank(message = "O nome do produto não deve estar em branco")
    @NotEmpty(message = "O nome do produto não deve estar em branco")
    private String nome;

    private String descricao;

    private String categoria;

    private String  fornecedor;

    private BigDecimal precoCompra;

    private BigDecimal precoVenda;
@Min(value = 0,message = "O valor da quantidade minima deve ser maior ou igual a 0")
    private Integer quanidadeMin;
@Min(value = 0 ,message = "O valor da quantidade Maxima deve " +
        "ser maior ou igual que o valor da quantidade minima")
    private Integer quantidadeMax;

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
    }

    public Produto_DTO (){
        super();
    }
}
