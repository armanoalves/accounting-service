package com.totvs.contasservice.application.usecases;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;

public class UpdateContaInterector {

    private final ContaGateway contaGateway;

    public UpdateContaInterector(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Conta update(Long id, Conta conta) {
        return contaGateway.update(id, conta);
    }
}
