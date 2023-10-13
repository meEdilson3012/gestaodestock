package com.gestaodestock.gestaodestock.domain.Repository;

import com.gestaodestock.gestaodestock.domain.DTOs.Produto_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

    List<Produto> findAllByNomeContaining (String nome);
    Produto findByNome(String nome);
    Optional<Produto_Listar_DTO> findByCategoria(String categoria);



}
