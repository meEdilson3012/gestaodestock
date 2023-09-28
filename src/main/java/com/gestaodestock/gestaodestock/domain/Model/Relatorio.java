package com.gestaodestock.gestaodestock.domain.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class Relatorio {

    private List<Entrada> entradas = new ArrayList<>();

    private List<Saida> saidas= new ArrayList<>();
    private String Descricao;
    private LocalDateTime data;


}
