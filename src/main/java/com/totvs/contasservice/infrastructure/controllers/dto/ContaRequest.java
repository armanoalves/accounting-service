package com.totvs.contasservice.infrastructure.controllers.dto;

import com.totvs.contasservice.domain.entity.Situacao;

import java.time.LocalDate;

public record ContaRequest(
        LocalDate dataVencimento,
        LocalDate datePagamento,
        Double valor,
        String descricao,
        Situacao situacao
) {
}
