package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.ContaFiltro;

import java.util.List;

public interface GetAllContaUseCase {
    List<Conta> getAllConta(ContaFiltro filtro, int page, int size);
}
