package com.totvs.contasservice.application.usecases;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;

public class CreateContaInterector {

    private final ContaGateway contaGateway;

    public CreateContaInterector(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Conta createConta(Conta conta) {
        return contaGateway.createConta(conta);
    }
}
