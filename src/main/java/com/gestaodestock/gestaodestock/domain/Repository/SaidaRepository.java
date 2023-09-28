package com.gestaodestock.gestaodestock.domain.Repository;

import com.gestaodestock.gestaodestock.domain.Model.Entrada;
import com.gestaodestock.gestaodestock.domain.Model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaidaRepository extends JpaRepository<Saida,Long> {
    List<Saida> findByProdutoId(Long Id);

}
