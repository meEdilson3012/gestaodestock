package com.gestaodestock.gestaodestock.domain.DTOs;

import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Entrada_Listar_DTO(Long id, int quantidade, LocalDateTime dataEntrada, int numeroLote, BigDecimal custoUnitorio, Produto produto) {


    public Entrada_Listar_DTO(Entrada entrada) {

        this(entrada.getId(), entrada.getQuantidade(), entrada.getDataEntrada(),
                entrada.getNumeroLote(), entrada.getCusto(), entrada.getProduto());


    }
}
/*
@Setter
public class Entrada_Listar_DTO{

    private Long Id;
}


    private int quantidade;


    private LocalDateTime dataEntrada;
    private int numeroLote;

    private BigDecimal custo;

   Produto_Listar_DTO produto_listar_dto ;


    public  Entrada_Listar_DTO (Entrada entrada){
        this.Id= entrada.getId();
        this.quantidade= entrada.getQuantidade();
        this.dataEntrada = entrada.getDataEntrada();
        this.numeroLote= entrada.getNumeroLote();
        this.custo= entrada.getCusto();
        this.produto_listar_dto= entradaDTO(entrada.getProduto());
    }

    public Produto_Listar_DTO entradaDTO(Produto produto){
        Produto_Listar_DTO produto_listar_dto1 = new Produto_Listar_DTO(produto);
        return  produto_listar_dto1;
    }
}
*/