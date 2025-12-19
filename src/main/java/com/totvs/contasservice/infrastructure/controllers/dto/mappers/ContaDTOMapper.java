package com.totvs.contasservice.infrastructure.controllers.dto.mappers;

import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaResponse;

public class ContaDTOMapper {
    public ContaResponse toResponse(Conta conta) {
        return new ContaResponse(
                conta.getId(),
                conta.getDataVencimento(),
                conta.getDataPagamento(),
                conta.getValor(),
                conta.getDescricao(),
                conta.getSituacao()
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
