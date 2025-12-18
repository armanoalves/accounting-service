package com.totvs.contasservice.infrastructure.persistence;

import com.totvs.contasservice.domain.entity.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ContaRepository extends JpaRepository<ContaEntity, Long>, JpaSpecificationExecutor<ContaEntity> {
    @Query("SELECT COALESCE(SUM(c.valor), 0) FROM ContaEntity c WHERE c.situacao = :situacao AND c.dataPagamento BETWEEN :inicio AND :fim")
    Double findTotalPagoPorPeriodo(@Param("situacao") Situacao situacao, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}

