package com.gestaodestock.gestaodestock.domain.DTOs;

import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Model.Saida;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class Saida_DTO {

    private Long Id;

    private int quantidade;


    private LocalDateTime dataSaida;

    private String motivoRetirada;

    private Produto produto;

    public Saida_DTO(Saida saida) {
        this.Id = saida.getId();
        this.quantidade = saida.getQuantidade();
        this.dataSaida = saida.getDataSaida();
        this.motivoRetirada = saida.getMotivoRetirada();
        this.produto = saida.getProduto();

    }

    public Saida_DTO (){
        super();
    }
}
