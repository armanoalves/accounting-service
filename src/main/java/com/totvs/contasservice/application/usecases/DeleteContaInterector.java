package com.totvs.contasservice.application.usecases;

import com.totvs.contasservice.application.gateways.ContaGateway;

public class DeleteContaInterector {

    private final ContaGateway contaGateway;

    public DeleteContaInterector(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public void delete(Long id) {
        contaGateway.delete(id);
    }
}
