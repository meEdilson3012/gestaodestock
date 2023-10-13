package com.gestaodestock.gestaodestock.Controller;

import com.gestaodestock.gestaodestock.domain.DTOs.Entrada_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Entrada_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeNaoEncontrada;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeemUso;
import com.gestaodestock.gestaodestock.domain.Exeptions.QuantidadeNaoValida;
import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Repository.EntradaRepository;
import com.gestaodestock.gestaodestock.domain.Service.EntradaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entradas")
public class EntradaController {
    @Autowired
    private EntradaRepository entradaRepository;
    @Autowired
    private EntradaService entradaService;
    @GetMapping
    public List<Entrada_Listar_DTO> listar(){
        List<Entrada_Listar_DTO> entrada_dtos = entradaRepository.findAll().stream().map(Entrada_Listar_DTO::new).toList();
       return entrada_dtos ;

    }

    @GetMapping("/{entradaId}")
    public ResponseEntity<Entrada_Listar_DTO> buscar(@PathVariable(required = true) Long entradaId){
        Entrada_Listar_DTO entrada_dto = entradaService.listarSingleton(entradaId);
        return ResponseEntity.ok(entrada_dto);
    }
    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid Entrada_DTO entrada_dto){
        try{
            entradaService.adicionar(entrada_dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    @PutMapping("/{entradaId}")
    public ResponseEntity<?> actualizar(@PathVariable(required = true)  Long entradaId, @RequestBody @Valid Entrada_DTO entrada_dto){

        Entrada_Listar_DTO entrada_dto1 =   entradaService.actualizar(entradaId,entrada_dto);
        return ResponseEntity.ok(entrada_dto1);

    }
    @DeleteMapping("/{entradaId}")
    public  ResponseEntity<?> deletar(@PathVariable(required = true) Long entradaId){
        try{
            entradaService.deletar(entradaId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontrada e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
