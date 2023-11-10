package com.gestaodestock.gestaodestock.domain.Model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
@Data
public class ControleDeStock {

    private String Item;
    private Integer entrada;
    private  Integer saida;
    private  Integer stock_final;
    private float girometrica;
    private float receitaTotal;
    private float custoTotal;
    private float lucroOUprezuizo;



}
