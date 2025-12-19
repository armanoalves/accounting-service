package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.domain.entity.Conta;

public interface CreateContaUseCase {
    Conta createConta(Conta conta);
}
