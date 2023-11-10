package com.gestaodestock.gestaodestock.domain.Service;

import com.gestaodestock.gestaodestock.domain.DTOs.Produto_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Produto_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeNaoEncontrada;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeemUso;
import com.gestaodestock.gestaodestock.domain.Exeptions.PrecoNaoValido;
import com.gestaodestock.gestaodestock.domain.Exeptions.QuantidadeMInima;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Repository.ProdutoRepository;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto_Listar_DTO listarSingleton(Long Id){
       Produto produto= buscarOUfalhar(Id);
        Produto_Listar_DTO produto_listar_dto = new Produto_Listar_DTO(produto);

        return  produto_listar_dto;

    }

    public List<Produto_Listar_DTO> buscaPorNome(String nome){
        List<Produto> produtos = produtoRepository.findAllByNomeContaining(nome);
        if (produtos.isEmpty()) {
            throw new EntidadeNaoEncontrada(String.format("O produto com o nome %s não foi encontrado",nome));

        }
        List<Produto_Listar_DTO> produto_listar_dto = produtos.stream().map(Produto_Listar_DTO::new).toList();
        return  produto_listar_dto;
    }
    public Produto_Listar_DTO buscaPorNomes(String nome){
        Produto produto = produtoRepository.findByNome(nome);
        if (produto == null) {
            throw new EntidadeNaoEncontrada(String.format("O produto com o nome %s não foi encontrado",nome));

        }
        Produto_Listar_DTO produto_listar_dto = new Produto_Listar_DTO(produto);
        return  produto_listar_dto;
    }
    public List<Produto_Listar_DTO> buscaPorCategoria(String categoria){

            List<Produto> produtos = produtoRepository.findByCategoria(categoria);
            if (produtos.isEmpty()) {
                throw new EntidadeNaoEncontrada(String.format("O produto com a categoria  %s não foram encontrados",categoria));

            }
            List<Produto_Listar_DTO> produto_listar_dto = produtos.stream().map(Produto_Listar_DTO::new).toList();
            return  produto_listar_dto;

    }
    public Optional<Produto_Listar_DTO> buscaPorFornecedores(String fornecedor){
        Optional<Produto_Listar_DTO> produto_listar_dto = produtoRepository.findByFornecedor(fornecedor);
        if (produto_listar_dto.isPresent()) {
            return  produto_listar_dto;
        }
        return null;
    }

    public Produto_Listar_DTO adicionar(Produto_DTO produto_dto){

        verificarPreco(produto_dto.getPrecoCompra(),produto_dto.getPrecoVenda());
        verificarQuantidade(produto_dto.getQuantidadeMax(),produto_dto.getQuantidadeMin());
        Produto produto = new Produto(produto_dto);
        Produto produtoSalvo=produtoRepository.save(produto);
        Produto_Listar_DTO produto_listar_dto = new Produto_Listar_DTO(produto);
        return produto_listar_dto;
    }



    public Produto_Listar_DTO actulizar(Long Id , Produto_DTO produto_dto){
       buscarOUfalhar(Id);
       produto_dto.setId(Id);
       Produto_Listar_DTO produto_listar_dto =adicionar(produto_dto);
       return  produto_listar_dto;
    }

    public void actulizar(Long Id , Integer quantidade){
       Produto produto= buscarOUfalhar(Id);
        produto.setId(Id);
        produto.setQuantidadeActual(quantidade);
        boolean estado = (quantidade >= produto.getQuanidadeMin())? true :false;
        produto.setEstado(estado);
        produtoRepository.save(produto);

    }
    public void deletar(Long Id) {
        try {
            buscarOUfalhar(Id);
            produtoRepository.deleteById(Id);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeemUso(String.format("O produto com codigo %d está em uso", Id));
        }

    }

    public  void verificarPreco(BigDecimal valorcompra , BigDecimal valorvenda){
       if (valorcompra.floatValue() > valorvenda.floatValue()){
           throw  new PrecoNaoValido("O preço da compra deve ser superior ao preco da venda ");
       }

    }

    public void verificarQuantidade(Integer quantidadeMax, Integer quantidadeMin){
        if (quantidadeMax < quantidadeMin){
            throw  new QuantidadeMInima("A quantidade maxima deve ser maior ou igual a quantidade minima ");
        }

    }

    public Produto buscarOUfalhar(Long Id){
        return produtoRepository.findById(Id)
                .orElseThrow(()-> new EntidadeNaoEncontrada(String.format("O produto com codigo %d nao foi encontrada", Id)));

    }

}


