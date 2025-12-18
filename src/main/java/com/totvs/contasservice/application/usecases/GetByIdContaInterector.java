package com.totvs.contasservice.application.usecases;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;

public class GetByIdContaInterector {

    private final ContaGateway contaGateWay;

    public GetByIdContaInterector(ContaGateway contaGateWay) {
        this.contaGateWay = contaGateWay;
    }

    public Conta findById(Long id) {
        return contaGateWay.findById(id);
    }
}
