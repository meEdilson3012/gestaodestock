package com.gestaodestock.gestaodestock.domain.Repository;

import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EntradaRepository extends JpaRepository<Entrada,Long> {

    List<Entrada> findByProdutoId(Long Id);
    Optional<Entrada> findByIdAndLixeira(Long Id, Boolean lixeira);

    List<Entrada> findByLixeira(Boolean lixeira);

}
