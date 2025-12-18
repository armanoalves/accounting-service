package com.totvs.contasservice.domain.entity;

import java.time.LocalDate;

public record Conta(
        Long id,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        Double valor,
        String descricao,
        Situacao situacao) {
}
