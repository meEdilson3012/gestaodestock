package com.gestaodestock.gestaodestock.domain.Repository;

import com.gestaodestock.gestaodestock.domain.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

    List<Produto> findAllByNomeContaining (String nome);
    Produto findByNome(String nome);
    Produto findByCategoria(String categoria);



}
