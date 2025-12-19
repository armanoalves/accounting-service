package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;

public class UpdateContaInteractor implements UpdateContaUseCase{

    private final ContaGateway contaGateway;

    public UpdateContaInteractor(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Conta updateConta(Long id, Conta conta) {
        return contaGateway.update(id, conta);
    }
}
