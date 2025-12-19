package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;

public class DeleteContaInteractor {

    private final ContaGateway contaGateway;

    public DeleteContaInteractor(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public void delete(Long id) {
        contaGateway.delete(id);
    }
}
