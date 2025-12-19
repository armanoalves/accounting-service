package com.totvs.contasservice.infrastructure.controllers.dto;

import com.totvs.contasservice.domain.entity.Situacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record ContaRequest(
                @NotNull(message = "A data de vencimento é obrigatória") LocalDate dataVencimento,
                LocalDate datePagamento,
                @NotNull(message = "O valor é obrigatório") @Positive(message = "O valor deve ser positivo") Double valor,
                @NotBlank(message = "A descrição é obrigatória") String descricao,
                @NotNull(message = "A situação é obrigatória") Situacao situacao) {
}
