package com.totvs.contasservice.application.usecases.conta;

import java.time.LocalDate;

public interface GetValorTotalPagoPorPeriodoUseCase {
    Double execute(LocalDate dataInicio, LocalDate dataFim);
}
