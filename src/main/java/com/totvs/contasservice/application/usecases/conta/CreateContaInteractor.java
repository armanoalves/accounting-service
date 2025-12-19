package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;

public class CreateContaInteractor {

    private final ContaGateway contaGateway;

    public CreateContaInteractor(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Conta createConta(Conta conta) {
        return contaGateway.createConta(conta);
    }
}
