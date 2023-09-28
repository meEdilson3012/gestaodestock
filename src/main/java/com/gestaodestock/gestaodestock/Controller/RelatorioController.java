package com.gestaodestock.gestaodestock.Controller;

import com.gestaodestock.gestaodestock.domain.Model.ControleDeStock;
import com.gestaodestock.gestaodestock.domain.Model.Relatorio;
import com.gestaodestock.gestaodestock.domain.Model.Saida;
import com.gestaodestock.gestaodestock.domain.Repository.EntradaRepository;
import com.gestaodestock.gestaodestock.domain.Repository.SaidaRepository;
import com.gestaodestock.gestaodestock.domain.Service.ControleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private ControleService controleService;
/*
    @GetMapping
    public Relatorio listar(){
        Relatorio relatorio = new Relatorio();
        relatorio.setEntradas(entradaRepository.findAll());
        relatorio.setSaidas(saidaRepository.findAll());
        return  relatorio;
    }
    */
    @GetMapping
    public List<ControleDeStock> listarControle(){

        return  controleService.mostrarControle();
    }
}
