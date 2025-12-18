package com.totvs.contasservice.application.usecases;

import com.totvs.contasservice.application.gateways.ContaGateway;

import java.time.LocalDate;

public class GetValorTotalPagoPorPeriodoInteractor {

    private final ContaGateway contaGateway;

    public GetValorTotalPagoPorPeriodoInteractor(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Double execute(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("Data de início deve ser anterior ou igual à data fim");
        }

        return contaGateway.getValorTotalPagoPorPeriodo(dataInicio, dataFim);
    }
}

