package com.totvs.contasservice.infrastructure.controllers;

import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaResponse;

public class ContaDTOMapper {
    ContaResponse toResponse(Conta conta) {
        return new ContaResponse(
                conta.id(),
                conta.dataVencimento(),
                conta.dataPagamento(),
                conta.valor(),
                conta.descricao(),
                conta.situacao()
        );
    }

    public Conta toConta(ContaRequest request) {
        return new Conta(
                null,
                request.dataVencimento(),
                request.datePagamento(),
                request.valor(),
                request.descricao(),
                request.situacao());
    }
}
