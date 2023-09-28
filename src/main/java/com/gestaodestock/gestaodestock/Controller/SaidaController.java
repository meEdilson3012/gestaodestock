package com.gestaodestock.gestaodestock.Controller;

import com.gestaodestock.gestaodestock.domain.DTOs.Saida_DTO;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeNaoEncontrada;
import com.gestaodestock.gestaodestock.domain.Exeptions.QuantidadeNaoValida;
import com.gestaodestock.gestaodestock.domain.Model.Saida;
import com.gestaodestock.gestaodestock.domain.Repository.SaidaRepository;
import com.gestaodestock.gestaodestock.domain.Service.SaidaService;
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
    public List<Saida> listar(){
        return  saidaRepository.findAll();
    }
    @GetMapping("/{saidaId}")
    public ResponseEntity<Saida_DTO> buscar(@PathVariable Long saidaId){
        Saida_DTO saida_dto  = saidaService.listarSingleton(saidaId);
        return ResponseEntity.ok(saida_dto);

    }
    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Saida_DTO saida_dto){
        try{
            Saida_DTO saidaSalva= saidaService.adicionar(saida_dto);
            return ResponseEntity.ok(saidaSalva);
        }catch (EntidadeNaoEncontrada e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/{saidaId}")
    public  ResponseEntity<?> actualizar(@PathVariable Long saidaId , @RequestBody Saida_DTO saida_dto){

        Saida_DTO saida_dto1 = saidaService.actualizar(saidaId, saida_dto);
        return ResponseEntity.ok(saida_dto1);
    }
    @DeleteMapping("/{saidaId}")
    public ResponseEntity<?> deletar(@PathVariable Long saidaId){
        try{
            saidaService.deletar(saidaId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontrada e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
