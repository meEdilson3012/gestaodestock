

package com.gestaodestock.gestaodestock.domain.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Relatorio {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String produto;
    @Column(nullable = false)
    private LocalDateTime data;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(nullable = false)
    private BigDecimal precoTotal;
    @Column(nullable = false)
    private  String descricao;



}
