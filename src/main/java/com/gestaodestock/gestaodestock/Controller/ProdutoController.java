package com.gestaodestock.gestaodestock.Controller;

import com.gestaodestock.gestaodestock.domain.DTOs.Produto_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Produto_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeNaoEncontrada;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeemUso;
import com.gestaodestock.gestaodestock.domain.Exeptions.PrecoNaoValido;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Repository.ProdutoRepository;
import com.gestaodestock.gestaodestock.domain.Service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;
    @GetMapping
    public List<Produto_Listar_DTO> listar(){
        List<Produto_Listar_DTO> produto_dtos = produtoRepository.findAll().stream().map(Produto_Listar_DTO::new).toList();
        return produto_dtos;
    }
    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto_Listar_DTO> buscar(@PathVariable(required = true) Long produtoId){
       Produto_Listar_DTO produto_dto= produtoService.listarSingleton(produtoId);
        if (produto_dto != null)
            return  ResponseEntity.ok(produto_dto);
        return  ResponseEntity.notFound().build();
    }
    @GetMapping("/nomes/{produtoNome}")
    public ResponseEntity<List<Produto_DTO>> buscarNome(@PathVariable(required = true) String produtoNome){
        List<Produto_DTO> produto_dtos = produtoRepository.findAllByNomeContaining(produtoNome).stream().map(Produto_DTO::new).toList();
        if (produto_dtos != null)
            return  ResponseEntity.ok(produto_dtos);

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{produtoNome}")
    public ResponseEntity<Produto_Listar_DTO> buscarNomeUnica(@PathVariable(required = true) String produtoNome){
        Produto_Listar_DTO produto_dto = produtoService.buscaPorNome(produtoNome);
        if (produto_dto != null)
            return  ResponseEntity.ok(produto_dto);
        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{produtoCategoria}")
    public ResponseEntity<?> buscarCategoria(@PathVariable(required = true) String produtoCategoria){
        Optional<Produto_Listar_DTO> produto_dto = produtoService.buscaPorCategoria(produtoCategoria);
        if (produto_dto.isPresent())
            return  ResponseEntity.ok(produto_dto.stream().toList());
        return  ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid Produto_DTO produto_dto){

        produtoService.adicionar(produto_dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{produtoId}")
    public  ResponseEntity<?> actualizar(@PathVariable(required = true) Long produtoId , @RequestBody @Valid Produto_DTO produto_dto) {

        produtoService.actulizar(produtoId, produto_dto);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/{produtoId}")
    public  ResponseEntity deletar(@PathVariable(required = true) Long produtoId){

       produtoService.deletar(produtoId);
       return  ResponseEntity.noContent().build();

    }

}
