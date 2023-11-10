package com.gestaodestock.gestaodestock.Controller;

import com.gestaodestock.gestaodestock.domain.DTOs.Entrada_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Saida_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Saida_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.Service.LixeiraService;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lixeira")
public class LixeiraController {

    @Autowired
    private LixeiraService lixeiraService;
    @GetMapping("/saida")
    public List<Saida_Listar_DTO> listarSaidaLixeira(){
        List<Saida_Listar_DTO> saida_listar_dtos = lixeiraService.listarSaidaLixeira();
        return  saida_listar_dtos;
    }
    @GetMapping("/entrada")
    public List<Entrada_Listar_DTO> listarEntradaLixeira(){
        List<Entrada_Listar_DTO> entrada_listar_dtos = lixeiraService.listarEntradaLixeira();
        return  entrada_listar_dtos;
    }

    @PutMapping("/restaurarSaida/{saidaId}")
    public ResponseEntity restaurarSaida(@PathVariable(required = true)Long saidaId){
        lixeiraService.restaurarSaida(saidaId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/restaurarEntrada/{entradaId}")
    public ResponseEntity restaurarEntrada(@PathVariable(required = true)Long entradaId){
        lixeiraService.restaurarEntrada(entradaId);
        return ResponseEntity.ok().build();
    }
}
