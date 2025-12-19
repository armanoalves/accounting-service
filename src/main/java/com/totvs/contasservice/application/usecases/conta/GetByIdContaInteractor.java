package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;

public class GetByIdContaInteractor implements GetByIdContaUseCase{

    private final ContaGateway contaGateWay;

    public GetByIdContaInteractor(ContaGateway contaGateWay) {
        this.contaGateWay = contaGateWay;
    }

    public Conta getById(Long id) {
        return contaGateWay.findById(id);
    }
}
