package com.gestaodestock.gestaodestock.domain.Service;

import com.gestaodestock.gestaodestock.domain.DTOs.Entrada_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Produto_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.entr;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeNaoEncontrada;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeemUso;
import com.gestaodestock.gestaodestock.domain.Exeptions.QuantidadeNaoValida;
import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Repository.EntradaRepository;
import com.gestaodestock.gestaodestock.domain.Repository.ProdutoRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;




    public Entrada_DTO listarSingleton(Long Id){
        Entrada entrada= buscarOUfalhar(Id);

       Entrada_DTO entrada_dto = new Entrada_DTO(entrada);
        return entrada_dto;

    }
    public Entrada_DTO adicionar(Entrada_DTO entrada_dto){

        Long produtoId= entrada_dto.getProduto().getId();
        Produto produto= produtoService.buscarOUfalhar(produtoId);
        verificarQuantidade(entrada_dto.getQuantidade(),
                produto.getQuantidadeMax(),produto.getQuanidadeMin());
        produto.setQuantidadeActual(produto.getQuantidadeActual()+ entrada_dto.getQuantidade());
        Produto_DTO produto_dto = new Produto_DTO(produto);
        produtoService.actulizar(produto_dto.getId(),produto_dto);
        entrada_dto.setProduto(produto);
        Entrada entrada = new Entrada(entrada_dto);
        entradaRepository.save(entrada);
        return  entrada_dto;
    }

    public Entrada_DTO actualizar(Long Id, Entrada_DTO entrada_dto){

        Entrada entrada= buscarOUfalhar(Id);
        entrada_dto.setId(entrada.getId());
        Entrada_DTO entradaSalva =adicionar(entrada_dto);
        return entradaSalva;

    }

    public void deletar(Long Id){
        try{
            entradaRepository.deleteById(Id);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontrada(String.format("A entrada com codigo %d não foi encontrada",Id));
        }

    }

    public void verificarQuantidade(Integer quantidade,Integer quantiadadeMax , Integer quantidadeMin){
        if ((quantidade < quantidadeMin) || (quantidade > quantiadadeMax ) ) {
            throw  new QuantidadeNaoValida(" A quantidade inserida deve ser menor ou maior ao quantidade maxima ou minima respectivamente");
        }

    }

    public Entrada buscarOUfalhar(Long Id){
        return entradaRepository.findById(Id)
                .orElseThrow(()-> new EntidadeNaoEncontrada(String.format("A entrada com codigo %d não foi encontrada",Id)));

    }
}
