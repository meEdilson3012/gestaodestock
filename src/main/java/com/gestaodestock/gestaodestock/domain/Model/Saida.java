package com.gestaodestock.gestaodestock.domain.Model;

import com.gestaodestock.gestaodestock.domain.DTOs.Saida_DTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
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
    private int quantidade;
    private LocalDateTime dataSaida;
    @Column(length = 50)
    private String motivoRetirada;
    @Column(nullable = false)
    private BigDecimal custo;


    @ManyToOne
    @JoinColumn(name = "produtoId")
    Produto produto;

    private  boolean lixeira;
    public Saida(Saida_DTO saida_dto) {
        Id = saida_dto.getId();
        this.quantidade = saida_dto.getQuantidade();

        this.motivoRetirada = saida_dto.getMotivoRetirada();
        this.produto = saida_dto.getProduto();
        this.custo=BigDecimal.valueOf( saida_dto.getProduto().getPrecoVenda().intValue() * quantidade);
    }
    public Saida (){
        super();
    }


}
