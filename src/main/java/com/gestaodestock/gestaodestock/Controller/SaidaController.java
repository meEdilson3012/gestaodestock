package com.gestaodestock.gestaodestock.Controller;

import com.gestaodestock.gestaodestock.domain.DTOs.Saida_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Saida_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeNaoEncontrada;
import com.gestaodestock.gestaodestock.domain.Exeptions.QuantidadeNaoValida;
import com.gestaodestock.gestaodestock.domain.Model.Saida;
import com.gestaodestock.gestaodestock.domain.Repository.SaidaRepository;
import com.gestaodestock.gestaodestock.domain.Service.SaidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saidas")
public class SaidaController {

    @Autowired
    private SaidaRepository saidaRepository;
    @Autowired
    private SaidaService saidaService;

    @GetMapping
    public List<Saida_Listar_DTO> listar(){
        List<Saida_Listar_DTO> saida_dtos =saidaRepository.findAll().stream().map(Saida_Listar_DTO::new).toList();
        return  saida_dtos;
    }
    @GetMapping("/{saidaId}")
    public ResponseEntity<Saida_Listar_DTO> buscar(@PathVariable(required = true) Long saidaId){
        Saida_Listar_DTO saida_dto  = saidaService.listarSingleton(saidaId);
        return ResponseEntity.ok(saida_dto);

    }
    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid Saida_DTO saida_dto){
        try{
            Saida_Listar_DTO saidaSalva= saidaService.adicionar(saida_dto);
            return ResponseEntity.ok(saidaSalva);
        }catch (EntidadeNaoEncontrada e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/{saidaId}")
    public  ResponseEntity<?> actualizar(@PathVariable(required = true) Long saidaId , @RequestBody @Valid Saida_DTO saida_dto){

        Saida_Listar_DTO saida_dto1 = saidaService.actualizar(saidaId, saida_dto);
        return ResponseEntity.ok(saida_dto1);
    }
    @DeleteMapping("/{saidaId}")
    public ResponseEntity<?> deletar(@PathVariable(required = true) Long saidaId){
        try{
            saidaService.deletar(saidaId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontrada e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
