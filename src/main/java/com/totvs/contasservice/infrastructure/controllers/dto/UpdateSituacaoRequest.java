package com.totvs.contasservice.infrastructure.controllers.dto;

import com.totvs.contasservice.domain.entity.Situacao;
import jakarta.validation.constraints.NotNull;

public record UpdateSituacaoRequest(
        @NotNull(message = "A situação é obrigatória") Situacao situacao) {
}
