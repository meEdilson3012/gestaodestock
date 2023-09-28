package com.gestaodestock.gestaodestock.domain.Service;

import com.gestaodestock.gestaodestock.domain.DTOs.Produto_DTO;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeNaoEncontrada;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeemUso;
import com.gestaodestock.gestaodestock.domain.Exeptions.PrecoNaoValido;
import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto_DTO listarSingleton(Long Id){
       Produto produto= buscarOUfalhar(Id);
       Produto_DTO produto_dto = new Produto_DTO(produto);
        return  produto_dto;

    }
    public Produto_DTO buscaPorNome(String nome){
        Produto produto = produtoRepository.findByNome(nome);
        if (produto != null) {
            Produto_DTO produto_dto = new Produto_DTO(produto);
            return  produto_dto;
        }
        return null;
    }
    public Produto_DTO buscaPorCategoria(String categoria){
        Produto produto = produtoRepository.findByCategoria(categoria);
        if (produto != null) {
            Produto_DTO produto_dto = new Produto_DTO(produto);
            return  produto_dto;
        }
        return null;
    }

    public Produto_DTO adicionar(Produto_DTO produto_dto){

        verificarPreco(produto_dto.getPrecoCompra(),produto_dto.getPrecoVenda());
        produto_dto.setQuantidadeActual(0);
        Produto produto = new Produto(produto_dto);
        Produto produtoSalvo=produtoRepository.save(produto);
        Produto_DTO produto_dto1 =new Produto_DTO(produtoSalvo);
        return produto_dto1;
    }

    public Produto_DTO actulizar(Long Id , Produto_DTO produto_dto){
       buscarOUfalhar(Id);
       produto_dto.setId(Id);
       Produto_DTO produto =adicionar(produto_dto);
       return  produto;
    }
    public void deletar(Long Id) {
        try {
            produtoRepository.deleteById(Id);
        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(String.format("O produto com codigo %d nao foi encontrada", Id));
        }catch (DataIntegrityViolationException e) {
            throw new EntidadeemUso(String.format("O produto com codigo %d está em uso", Id));
        }

    }

    public  void verificarPreco(BigDecimal valorcompra , BigDecimal valorvenda){
       if (valorcompra.floatValue() > valorvenda.floatValue()){
           throw  new PrecoNaoValido("O preço da compra deve ser superior ao preco da venda ");
       }

    }

    public Produto buscarOUfalhar(Long Id){
        return produtoRepository.findById(Id)
                .orElseThrow(()-> new EntidadeNaoEncontrada(String.format("O produto com codigo %d nao foi encontrada", Id)));

    }

}


