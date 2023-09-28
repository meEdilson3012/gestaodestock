package com.gestaodestock.gestaodestock.domain.Service;

import com.gestaodestock.gestaodestock.domain.DTOs.Produto_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Saida_DTO;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeNaoEncontrada;
import com.gestaodestock.gestaodestock.domain.Exeptions.QuantidadeNaoValida;
import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Model.Saida;
import com.gestaodestock.gestaodestock.domain.Repository.ProdutoRepository;
import com.gestaodestock.gestaodestock.domain.Repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class SaidaService {
    @Autowired
    private SaidaRepository saidaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;


    public Saida_DTO listarSingleton(Long Id){
        Saida saida= buscarOUfalhar(Id);

        Saida_DTO saida_dto = new Saida_DTO(saida);
        return  saida_dto;

    }
    public Saida_DTO adicionar (Saida_DTO saida_dto){
        Long produtoId = saida_dto.getProduto().getId();
        Produto produto= produtoService.buscarOUfalhar(produtoId);
        verificarQuantidade(saida_dto.getQuantidade(),
                produto.getQuantidadeMax(),produto.getQuanidadeMin()) ;
        produto.setQuantidadeActual(produto.getQuantidadeActual()- saida_dto.getQuantidade());
        Produto_DTO produto_dto = new Produto_DTO(produto);
        produtoService.actulizar(produto_dto.getId(),produto_dto);
        saida_dto.setProduto(produto);
        Saida saida = new Saida(saida_dto);
        Saida_DTO saida_dtoSalva= new Saida_DTO(saidaRepository.save(saida));
        return saida_dtoSalva;
    }

    public Saida_DTO actualizar(Long Id ,Saida_DTO saida_dto){
        Saida saida= buscarOUfalhar(Id);

        saida_dto.setId(saida.getId());
        Saida_DTO saida_dtoSalva =adicionar(saida_dto);
        return  saida_dtoSalva;

    }

    public  void deletar(Long Id){
        try{
            saidaRepository.deleteById(Id);
        }catch (EmptyResultDataAccessException e){
            throw  new EntidadeNaoEncontrada(String.format("A saida com codigo %d não foi encontrada",Id));
        }
    }

    public void verificarQuantidade(Integer quantidade,Integer quantiadadeMax , Integer quantidadeMin){
        if ((quantidade < quantidadeMin) || (quantidade > quantiadadeMax ) ) {
            throw  new QuantidadeNaoValida(" A quantidade inserida deve ser menor ou maior ao quantidade maxima ou minima respectivamente");
        }

    }

    public Saida buscarOUfalhar(Long Id){
        return saidaRepository.findById(Id)
                .orElseThrow(()-> new EntidadeNaoEncontrada(String.format("A saida com codigo %d não foi encontrada",Id)));

    }
}
