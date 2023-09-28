package com.gestaodestock.gestaodestock.domain.Model;

import com.gestaodestock.gestaodestock.domain.DTOs.Saida_DTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Saida {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="coluna_quantidade")
    private int quantidade;

    @Column(name="coluna_dataSaida")
    @CreationTimestamp
    private LocalDateTime dataSaida;
    @Column(name="coluna_motivoRetirada", length = 50)
    private String motivoRetirada;

    @ManyToOne
    @JoinColumn(name = "produtoId")
    Produto produto;

    public Saida(Saida_DTO saida_dto) {
        Id = saida_dto.getId();
        this.quantidade = saida_dto.getQuantidade();
        this.dataSaida = saida_dto.getDataSaida();
        this.motivoRetirada = saida_dto.getMotivoRetirada();
        this.produto = produto;
    }
    public Saida (){
        super();
    }
}
