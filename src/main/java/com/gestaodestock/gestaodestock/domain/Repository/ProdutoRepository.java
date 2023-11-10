package com.gestaodestock.gestaodestock.domain.Repository;

import com.gestaodestock.gestaodestock.domain.DTOs.Produto_Listar_DTO;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import static org.hibernate.sql.ast.Clause.SELECT;
import static org.hibernate.sql.ast.Clause.WHERE;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

    List<Produto> findAllByNomeContaining (String nome);
    Produto findByNome(String nome);
    List<Produto>  findByCategoria(String categoria);

    List<Produto> findByEstado(boolean estado);
    Optional<Produto_Listar_DTO> findByFornecedor(String categoria);




}
