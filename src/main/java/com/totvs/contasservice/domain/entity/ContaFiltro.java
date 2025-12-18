package com.totvs.contasservice.domain.entity;

import java.time.LocalDate;

public record ContaFiltro(
        LocalDate dataVencimento,
        String descricao
) {}
