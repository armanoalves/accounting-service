package com.totvs.contasservice.infrastructure.controllers.dto;

import com.totvs.contasservice.domain.entity.Situacao;

import java.time.LocalDate;

public record ContaResponse(
        Long id,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        Double valor,
        String descricao,
        Situacao situacao
) {
}
