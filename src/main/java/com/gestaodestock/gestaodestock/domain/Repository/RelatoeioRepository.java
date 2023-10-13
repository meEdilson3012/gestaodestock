package com.gestaodestock.gestaodestock.domain.Repository;

import com.gestaodestock.gestaodestock.domain.Model.Produto;
import com.gestaodestock.gestaodestock.domain.Model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatoeioRepository extends JpaRepository<Relatorio,Long> {
}
