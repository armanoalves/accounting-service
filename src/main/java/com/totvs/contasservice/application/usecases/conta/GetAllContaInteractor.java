package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.ContaFiltro;

import java.util.List;

public class GetAllContaInteractor implements GetAllContaUseCase {

    private final ContaGateway contaGateway;

    public GetAllContaInteractor(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public List<Conta> getAllConta(ContaFiltro filtro, int page, int size) {
        return contaGateway.getAllConta(filtro, page, size);
    }
}
