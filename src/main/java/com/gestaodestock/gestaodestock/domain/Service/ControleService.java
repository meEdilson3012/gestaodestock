package com.gestaodestock.gestaodestock.domain.Service;

import com.gestaodestock.gestaodestock.domain.DTOs.Relatorio_DTO;
import com.gestaodestock.gestaodestock.domain.Model.*;
import com.gestaodestock.gestaodestock.domain.Repository.EntradaRepository;
import com.gestaodestock.gestaodestock.domain.Repository.ProdutoRepository;
import com.gestaodestock.gestaodestock.domain.Repository.RelatoeioRepository;
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
    @Autowired
    private RelatoeioRepository relatoeioRepository;


    public  List<ControleDeStock> mostrarControle(){

        List<ControleDeStock> controleDeStocks = new ArrayList<>();
        List<Produto> produtos = produtoRepository.findAll();

        for (Produto produto:produtos) {

            ControleDeStock controleDeStock = new ControleDeStock();
            controleDeStock.setItem(produto.getNome()) ;
            controleDeStock.setEntrada(contarEntradas(entradaRepository.findByProdutoId(produto.getId()))) ;
            controleDeStock.setSaida(contarSaidas(saidaRepository.findByProdutoId(produto.getId())));
            controleDeStock.setStock_final(controleDeStock.getEntrada()- controleDeStock.getSaida());
            float metrica= (controleDeStock.getSaida()/(controleDeStock.getStock_final()/2));
            controleDeStock.setGirometrica(metrica);
            controleDeStock.setReceitaTotal(produto.getPrecoVenda().floatValue() * controleDeStock.getSaida());
            controleDeStock.setCustoTotal(produto.getPrecoCompra().floatValue() *controleDeStock.getEntrada());
            controleDeStock.setLucroOUprezuizo(controleDeStock.getReceitaTotal() - controleDeStock.getCustoTotal());
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


    public  void cadastroRelatorio(Entrada entrada , String descricao,Boolean valor){

            Relatorio relatorio = new Relatorio();
            relatorio.setProduto(entrada.getProduto().getNome());
            relatorio.setTipo("Entrada");
            relatorio.setData(entrada.getDataEntrada());
            relatorio.setQuantidade(entrada.getQuantidade());
            relatorio.setPrecoTotal(BigDecimal.valueOf(entrada.getProduto().getPrecoCompra().intValue() + entrada.getQuantidade()));
            relatorio.setDescricao((valor == true)?descricao:"Actualização do Produto em Stock");
            Relatorio relatorioSalvo = relatoeioRepository.save(relatorio);

    }

    public  void cadastroRelatorio(Saida saida,String descricao,Boolean valor){

            Relatorio relatorio = new Relatorio();
            relatorio.setProduto(saida.getProduto().getNome());
            relatorio.setTipo("Saida");
            relatorio.setData(saida.getDataSaida());
            relatorio.setQuantidade(saida.getQuantidade());
            relatorio.setPrecoTotal(BigDecimal.valueOf(saida.getProduto().getPrecoVenda().intValue() + saida.getQuantidade()));
            relatorio.setDescricao((valor == true)?descricao:"Actualização do Produto em Stock");
            Relatorio relatorioSalvo = relatoeioRepository.save(relatorio);

    }
    public List<Produto> verificarEstoque(){
       List<Produto> produtos= produtoRepository.findByEstado(false);
        return produtos;
    }
}
