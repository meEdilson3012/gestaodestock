package com.gestaodestock.gestaodestock.Controller;

import com.gestaodestock.gestaodestock.domain.DTOs.Relatorio_DTO;
import com.gestaodestock.gestaodestock.domain.Model.ControleDeStock;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Repository.RelatoeioRepository;
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
    @Autowired
    private RelatoeioRepository relatoeioRepository;

    @GetMapping("/movimentacao")
    public List<Relatorio_DTO> listar(){
        List<Relatorio_DTO> relatorio_dtos= relatoeioRepository.findAll().stream().map(Relatorio_DTO::new).toList();
        return  relatorio_dtos;
    }

    @GetMapping("/controle")
    public List<ControleDeStock> listarControle(){

        return  controleService.mostrarControle();
    }

    @GetMapping("/alertadestock")
    public List<Produto> listarprod(){

        return  controleService.verificarEstoque();
    }
}
