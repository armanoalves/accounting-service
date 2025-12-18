package com.totvs.contasservice.infrastructure.controllers.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PeriodoPagamentoRequest(
        @NotNull(message = "dataInicio é obrigatória")
        LocalDate dataInicio,

        @NotNull(message = "dataFim é obrigatória")
        LocalDate dataFim
) {}

