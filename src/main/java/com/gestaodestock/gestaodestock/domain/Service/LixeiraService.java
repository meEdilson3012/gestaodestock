package com.gestaodestock.gestaodestock.domain.Service;


import com.gestaodestock.gestaodestock.domain.DTOs.Entrada_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Entrada_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Saida_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Saida_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Model.Saida;
import com.gestaodestock.gestaodestock.domain.Repository.EntradaRepository;
import com.gestaodestock.gestaodestock.domain.Repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LixeiraService {

    @Autowired
    private SaidaRepository saidaRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private  SaidaService saidaService;

    @Autowired
    private  EntradaService entradaService;

    public List<Saida_Listar_DTO> listarSaidaLixeira(){
        List<Saida_Listar_DTO> saida_listar_dtos= saidaRepository.findByLixeira(true)
                .stream().map(Saida_Listar_DTO::new).toList();
        return saida_listar_dtos;
    }

    public List<Entrada_Listar_DTO> listarEntradaLixeira(){
        List<Entrada_Listar_DTO> entrada_listar_dtos= entradaRepository.findByLixeira(true)
                .stream().map(Entrada_Listar_DTO::new).toList();
        return entrada_listar_dtos;
    }

    public void restaurarSaida(Long id){
       Saida saida= saidaService.buscarOUfalhar(id);
       saida.setId(id);
       saida.setLixeira(false);
       Produto produto= saidaService.deletarquantidade(true,saida);
       saida.setProduto(produto);
        Saida_DTO saida_dto = new Saida_DTO(saida);
       saidaService.adicionar(saida_dto);
    }

    public  void restaurarEntrada(Long id){
        Entrada entrada = entradaService.buscarOUfalhar(id);
        entrada.setId(id);
        entrada.setLixeira(false);
        Produto produto= entradaService.deletarquantidade(true,entrada);
        entrada.setProduto(produto);
        Entrada_DTO entrada_dto = new Entrada_DTO(entrada);
        entradaService.adicionar(entrada_dto);
    }
}
