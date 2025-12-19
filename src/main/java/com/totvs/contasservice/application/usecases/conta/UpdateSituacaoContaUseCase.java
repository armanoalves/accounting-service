package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.Situacao;

public interface UpdateSituacaoContaUseCase {
    Conta updateSituacao(Long id, Situacao situacao);
}
