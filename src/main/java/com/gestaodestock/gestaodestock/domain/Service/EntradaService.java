package com.gestaodestock.gestaodestock.domain.Service;

import com.gestaodestock.gestaodestock.domain.DTOs.Entrada_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Entrada_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Produto_DTO;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeNaoEncontrada;
import com.gestaodestock.gestaodestock.domain.Exeptions.QuantidadeNaoValida;
import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Repository.EntradaRepository;
import com.gestaodestock.gestaodestock.domain.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private  ControleService controleService;




    public Entrada_Listar_DTO listarSingleton(Long Id){
        Entrada entrada= buscarOUfalhar(Id);
        Entrada_Listar_DTO entrada_listar_dto= new Entrada_Listar_DTO(entrada);
        return entrada_listar_dto;

    }
    public Entrada_Listar_DTO adicionar(Entrada_DTO entrada_dto){

        Long produtoId= entrada_dto.getProduto().getId();
        Produto produto= produtoService.buscarOUfalhar(produtoId);
        verificarQuantidade(entrada_dto.getQuantidade(),
                produto.getQuantidadeMax(),produto.getQuanidadeMin());
        entrada_dto.setProduto(produto);
        Entrada entrada = new Entrada(entrada_dto);
        Boolean relatorio= buscarEntrada(entrada);
        Produto produto1= actualizarQuantidade(relatorio,entrada);
        entrada.setProduto(produto1);
        entrada.setDataEntrada(LocalDateTime.now());
        entradaRepository.save(entrada);
        controleService.cadastroRelatorio(entrada,"Entrada do produto",relatorio);
        Entrada_Listar_DTO entrada_listar_dto= new Entrada_Listar_DTO(entrada);
        return  entrada_listar_dto;
    }

    public Entrada_Listar_DTO actualizar(Long Id, Entrada_DTO entrada_dto){

        Entrada entrada= buscarOUfalhar(Id);
        entrada_dto.setId(entrada.getId());
        Entrada_Listar_DTO entradaSalva =adicionar(entrada_dto);
        return entradaSalva;

    }

    public void deletar(Long Id){
        buscarOUfalhar(Id);
        entradaRepository.deleteById(Id);

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
    public Boolean buscarEntrada(Entrada entrada){
       if (entrada.getId() ==null){
           return true;
       }
       return false;
    }

    public Produto actualizarQuantidade(Boolean verificar,Entrada entrada){
        if (verificar==false){
            Optional<Entrada> entrada1 = entradaRepository.findById(entrada.getId());
            Integer quantidadeAnterior= entrada1.get().getQuantidade();
            Integer quantidadeActual = entrada.getQuantidade();
            Integer quantidadeF= quantidadeActual - quantidadeAnterior;
                entrada.getProduto().setQuantidadeActual(entrada.getProduto().getQuantidadeActual()+ quantidadeF);


        }else {
            entrada.getProduto().setQuantidadeActual(entrada.getProduto().getQuantidadeActual()+ entrada.getQuantidade());

        }
        produtoService.actulizar(entrada.getProduto().getId(),entrada.getProduto().getQuantidadeActual());
        return  entrada.getProduto();
    }
}
