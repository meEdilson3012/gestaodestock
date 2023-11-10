package com.gestaodestock.gestaodestock.domain.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @Column( length = 30 ,nullable = false)
    private String nome;
    @Column( length = 200)
    private String descricao;
    @Column( length = 30)
    private String categoria;
    @Column(length = 30)
    private String  fornecedor;
    private BigDecimal precoCompra;
    private BigDecimal precoVenda;
    @Column(length = 30,nullable = false)
    private int quanidadeMin;



    @Column(length = 30, nullable = false)
    private int quantidadeMax;
    @Column(length = 30)
    private int quantidadeActual;

    @Column(nullable = false)
    @JsonIgnore
    private boolean estado;


    public Produto(Produto_DTO produto_dto) {
      this.Id = produto_dto.getId();
      this.nome = produto_dto.getNome();
      this.descricao = produto_dto.getDescricao();
      this.categoria = produto_dto.getCategoria();
      this.fornecedor = produto_dto.getFornecedor();
      this.precoCompra = produto_dto.getPrecoCompra();
      this.precoVenda = produto_dto.getPrecoVenda();
      this.quanidadeMin = produto_dto.getQuantidadeMin();
      this.quantidadeMax = produto_dto.getQuantidadeMax();
      this.estado = true;

    }


    public  Produto(){
        super();
    }

}
