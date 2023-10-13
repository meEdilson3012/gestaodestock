package com.gestaodestock.gestaodestock.domain.DTOs;

import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Model.Saida;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class Saida_DTO {

    private Long Id;
@NotNull(message = "O valor da quantidade não deve ser nula")
    private int quantidade;
    private String motivoRetirada;
    @NotNull
    private Produto produto;

    public Saida_DTO(Saida saida) {
        this.Id = saida.getId();
        this.quantidade = saida.getQuantidade();
        this.motivoRetirada = saida.getMotivoRetirada();
        this.produto = saida.getProduto();

    }

    public Saida_DTO (){
        super();
    }
}
