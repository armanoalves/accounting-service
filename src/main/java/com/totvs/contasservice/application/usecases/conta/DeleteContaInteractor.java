package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;

public class DeleteContaInteractor implements DeleteContaUseCase{

    private final ContaGateway contaGateway;

    public DeleteContaInteractor(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public void deleteConta(Long id) {
        contaGateway.delete(id);
    }
}
