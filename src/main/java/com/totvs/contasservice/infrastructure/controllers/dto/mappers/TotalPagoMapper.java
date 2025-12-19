package com.totvs.contasservice.infrastructure.controllers.dto.mappers;

import com.totvs.contasservice.infrastructure.controllers.dto.TotalPagoResponse;

import java.time.LocalDate;

public class TotalPagoMapper {

    public TotalPagoResponse toResponse(LocalDate inicio, LocalDate fim, Double total) {
        return new TotalPagoResponse(inicio, fim, total);
    }

}

