package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.Situacao;

public class UpdateSituacaoContaInteractor implements UpdateSituacaoContaUseCase {

    private final ContaGateway contaGateway;

    public UpdateSituacaoContaInteractor(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    @Override
    public Conta updateSituacao(Long id, Situacao situacao) {
        return contaGateway.updateSituacao(id, situacao);
    }
}
