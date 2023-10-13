package com.gestaodestock.gestaodestock.domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaodestock.gestaodestock.domain.DTOs.Entrada_DTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
@Entity
public class Entrada {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="coluna_quantidade",nullable = false)
    private int quantidade;

    @Column(name="coluna_dataEntrada")
    @CreationTimestamp
    private LocalDateTime dataEntrada;
    @Column(name="coluna_numeroLote")
    private int numeroLote;
    @Column(name = "coluna_custo",nullable = false)
    private BigDecimal custo;
    @ManyToOne
    @JoinColumn(name = "produtoId")
    Produto produto;

    public Entrada (Entrada_DTO entrada_dto) {
        this.Id = entrada_dto.getId();
        this.quantidade = entrada_dto.getQuantidade();
        this.numeroLote = entrada_dto.getNumeroLote();
        this.produto = entrada_dto.getProduto();
        this.custo=BigDecimal.valueOf( entrada_dto.getProduto().getPrecoCompra().intValue() * quantidade);
    }

    public  Entrada(){
        super();
    }

}
