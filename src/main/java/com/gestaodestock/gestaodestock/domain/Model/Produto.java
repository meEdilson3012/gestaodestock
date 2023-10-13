package com.gestaodestock.gestaodestock.domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaodestock.gestaodestock.domain.DTOs.Produto_DTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {
   @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="coluna_nome", length = 30 ,nullable = false)
    private String nome;
    @Column(name="coluna_descricao", length = 30)
    private String descricao;
    @Column(name="coluna_categoria", length = 30)
    private String categoria;
    @Column(name="coluna_fornecedor", length = 30)
    private String  fornecedor;
    @Column(name="coluna_precoCompra")
    private BigDecimal precoCompra;
    @Column(name="coluna_precoVenda", length = 30)
    private BigDecimal precoVenda;
    @Column(name="coluna_quantidadeMin", length = 30,nullable = false)
    private int quanidadeMin;

    @Column(name="coluna_quantidadeMax", length = 30, nullable = false)
    private int quantidadeMax;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name="coluna_quantidadeActual", length = 30)
    private int quantidadeActual;



    public Produto(Produto_DTO produto_dto) {
      this.Id = produto_dto.getId();
      this.nome = produto_dto.getNome();
      this.descricao = produto_dto.getDescricao();
      this.categoria = produto_dto.getCategoria();
      this.fornecedor = produto_dto.getFornecedor();
      this.precoCompra = produto_dto.getPrecoCompra();
      this.precoVenda = produto_dto.getPrecoVenda();
      this.quanidadeMin = produto_dto.getQuanidadeMin();
      this.quantidadeMax = produto_dto.getQuantidadeMax();
    }


    public  Produto(){
        super();
    }

}
