package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.domain.entity.Conta;

public interface UpdateContaUseCase {
    Conta updateConta(Long id, Conta conta);
}
