package com.gestaodestock.gestaodestock.Controller;

import com.gestaodestock.gestaodestock.domain.DTOs.Produto_DTO;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeNaoEncontrada;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeemUso;
import com.gestaodestock.gestaodestock.domain.Exeptions.PrecoNaoValido;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Repository.ProdutoRepository;
import com.gestaodestock.gestaodestock.domain.Service.ProdutoService;
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
    public List<Produto> listar(){

        return produtoRepository.findAll();
    }
    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto_DTO> buscar(@PathVariable Long produtoId){
       Produto_DTO produto_dto= produtoService.listarSingleton(produtoId);
        if (produto_dto != null)
            return  ResponseEntity.ok(produto_dto);
        return  ResponseEntity.notFound().build();
    }
    @GetMapping("/nome/{produtoNome}")
    public List<Produto> buscarNome(@PathVariable String produtoNome){
        List<Produto> produto = produtoRepository.findAllByNomeContaining(produtoNome);
        if (produto != null)
            return  produto;
        return  null;
    }

    @GetMapping("/nome/{produtoNome}")
    public ResponseEntity<Produto_DTO> buscarNomeUnica(@PathVariable String produtoNome){
        Produto_DTO produto_dto = produtoService.buscaPorNome(produtoNome);
        if (produto_dto != null)
            return  ResponseEntity.ok(produto_dto);
        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{produtoCategoria}")
    public ResponseEntity<Produto_DTO> buscarCategoria(@PathVariable String produtoCategoria){
        Produto_DTO produto_dto = produtoService.buscaPorCategoria(produtoCategoria);
        if (produto_dto != null)
            return  ResponseEntity.ok(produto_dto);
        return  ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Produto_DTO produto_dto){
        try{
            produtoService.adicionar(produto_dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (PrecoNaoValido e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PutMapping("/{produtoId}")
    public  ResponseEntity<?> actualizar(@PathVariable Long produtoId , @RequestBody Produto_DTO produto_dto) {
        try {
            produtoService.actulizar(produtoId, produto_dto);
            return ResponseEntity.ok().build();
        } catch (PrecoNaoValido e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntidadeNaoEncontrada ee){
        return ResponseEntity.badRequest().body(ee.getMessage());
       }
    }
    @DeleteMapping("/{produtoId}")
    public  ResponseEntity deletar(@PathVariable Long produtoId){
        Optional<Produto> produto= produtoRepository.findById(produtoId);
        try {
           produtoService.deletar(produtoId);
            return  ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontrada e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (EntidadeemUso e){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

}
