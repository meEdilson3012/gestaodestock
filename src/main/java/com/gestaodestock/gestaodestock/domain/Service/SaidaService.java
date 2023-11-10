package com.gestaodestock.gestaodestock.domain.Service;

import com.gestaodestock.gestaodestock.domain.DTOs.Produto_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Saida_DTO;
import com.gestaodestock.gestaodestock.domain.DTOs.Saida_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.Exeptions.EntidadeApagada;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaidaService {
    @Autowired
    private SaidaRepository saidaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    ControleService controleService;


    public List<Saida_Listar_DTO> listarSaida(){
        List<Saida_Listar_DTO> saida_listar_dtos = saidaRepository.findByLixeira(false)
                .stream().map(Saida_Listar_DTO::new).toList();
        return saida_listar_dtos;
    }

    public Saida_Listar_DTO listarSingleton(Long Id){
        Saida saida= buscarOUfalhar(Id);
        if (saida.isLixeira()){
            throw new EntidadeApagada(String.format("A saida com codigo %d está na lixeira. Faça uma restaração antes",Id));
        }
        Saida_Listar_DTO saida_listar_dto = new Saida_Listar_DTO(saida);
        return  saida_listar_dto;

    }
    public Saida_Listar_DTO adicionar (Saida_DTO saida_dto){
        Long produtoId = saida_dto.getProduto().getId();
        Produto produto= produtoService.buscarOUfalhar(produtoId);
        verificarQuantidade(saida_dto.getQuantidade(),
                produto.getQuantidadeMax(),produto.getQuanidadeMin()) ;
        saida_dto.setProduto(produto);
        Saida saida = new Saida(saida_dto);
        Boolean relatorio= buscarSaida(saida);
        Produto produto1= actualizarQuantidade(relatorio,saida);
        saida.setProduto(produto1);
        saida.setDataSaida(LocalDateTime.now());
        saidaRepository.save(saida);
        controleService.cadastroRelatorio(saida,"Saida do produto em stock",relatorio);
        Saida_Listar_DTO saida_listar_dto = new Saida_Listar_DTO(saida);
        return saida_listar_dto;
    }

    public Saida_Listar_DTO actualizar(Long Id ,Saida_DTO saida_dto){
        Saida saida= buscarOUfalhar(Id);

        saida_dto.setId(saida.getId());
        Saida_Listar_DTO saida_dtoSalva =adicionar(saida_dto);
        return  saida_dtoSalva;

    }

    public  void deletar(Long Id){
       Saida saida= buscarOUfalhar(Id);
       saida.setId(Id);
       saida.setLixeira(true);
       Produto produto= deletarquantidade(false,saida);
       saida.setProduto(produto);
       saidaRepository.save(saida);

    }

    public void verificarQuantidade(Integer quantidade,Integer quantiadadeMax , Integer quantidadeMin){
        if ((quantidade < quantidadeMin) || (quantidade > quantiadadeMax ) ) {
            throw  new QuantidadeNaoValida(" A quantidade inserida deve ser menor ou maior ao quantidade maxima ou minima respectivamente");
        }

    }

    public Saida buscarOUfalhar(Long Id){
        return saidaRepository.findByIdAndLixeira(Id,false)
                .orElseThrow(()-> new EntidadeNaoEncontrada(String.format("A saida com codigo %d não foi encontrada",Id)));

    }
    public Boolean buscarSaida(Saida saida){

        if (saida.getId() ==null){
            return true;
        }
        return false;
    }

    public Produto actualizarQuantidade(Boolean verificar,Saida saida){
        if (verificar==false){
            Optional<Saida> saida1 = saidaRepository.findById(saida.getId());
            Integer quantidadeAnterior= saida1.get().getQuantidade();
            Integer quantidadeActual = saida.getQuantidade();
            Integer quantidadeF= quantidadeActual - quantidadeAnterior;

                saida.getProduto().setQuantidadeActual(saida.getProduto().getQuantidadeActual()- quantidadeF);


        }else {
            saida.getProduto().setQuantidadeActual(saida.getProduto().getQuantidadeActual()-saida.getQuantidade());

        }
        produtoService.actulizar(saida.getProduto().getId(),saida.getProduto().getQuantidadeActual());
        return  saida.getProduto();
    }

    public  Produto deletarquantidade(Boolean verificar , Saida saida){
        if (verificar== true){
            saida.getProduto().setQuantidadeActual(saida.getProduto().getQuantidadeActual()-saida.getQuantidade());
        }else {
            saida.getProduto().setQuantidadeActual(saida.getProduto().getQuantidadeActual()+saida.getQuantidade());
        }
        produtoService.actulizar(saida.getProduto().getId(),saida.getProduto().getQuantidadeActual());
        return  saida.getProduto();
    }
}
