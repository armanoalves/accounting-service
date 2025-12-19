package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;

public class UpdateContaInteractor {

    private final ContaGateway contaGateway;

    public UpdateContaInteractor(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Conta update(Long id, Conta conta) {
        return contaGateway.update(id, conta);
    }
}
