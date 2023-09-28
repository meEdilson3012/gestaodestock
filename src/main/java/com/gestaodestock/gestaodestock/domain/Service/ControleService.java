package com.gestaodestock.gestaodestock.domain.Service;

import com.gestaodestock.gestaodestock.domain.Model.ControleDeStock;
import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Model.Saida;
import com.gestaodestock.gestaodestock.domain.Repository.EntradaRepository;
import com.gestaodestock.gestaodestock.domain.Repository.ProdutoRepository;
import com.gestaodestock.gestaodestock.domain.Repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ControleService {

    @Autowired
    private EntradaRepository entradaRepository;
    @Autowired
    private SaidaRepository saidaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;


    public  List<ControleDeStock> mostrarControle(){

        List<ControleDeStock> controleDeStocks = new ArrayList<>();
        List<Produto> produtos = produtoRepository.findAll();

        for (Produto produto:produtos) {
            ControleDeStock controleDeStock = new ControleDeStock();
            controleDeStock.setItem(produto.getNome()) ;
            controleDeStock.setEntrada(contarEntradas(entradaRepository.findByProdutoId(produto.getId()))) ;
            controleDeStock.setSaida(contarSaidas(saidaRepository.findByProdutoId(produto.getId())));
            controleDeStock.setStock_final(controleDeStock.getEntrada()- controleDeStock.getSaida());
            BigDecimal metrica= BigDecimal.valueOf((controleDeStock.getSaida()/(controleDeStock.getStock_final()/2)));
            controleDeStock.setGirometrica(metrica);
            controleDeStocks.add(controleDeStock);

        }
        return controleDeStocks;
    }

    public  Integer contarEntradas(List<Entrada>  entradas){
        Integer quantidade= 0;
        float saldo=0;
        for (Entrada entrada:entradas) {
            quantidade+= entrada.getQuantidade();

        }
        return quantidade;
    }
    public  Integer contarSaidas(List<Saida> saidas){
        Integer quantidade= 0;
        for (Saida saida:saidas) {
            quantidade+= saida.getQuantidade();
        }
        return quantidade;
    }


    public  void relatorio(List<Saida> saidas,List<Entrada>  entradas ){

    }
}
