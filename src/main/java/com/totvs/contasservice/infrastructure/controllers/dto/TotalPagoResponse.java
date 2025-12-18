package com.totvs.contasservice.infrastructure.controllers.dto;

import java.time.LocalDate;

public record TotalPagoResponse(
        LocalDate inicio,
        LocalDate fim,
        Double total
) {}

