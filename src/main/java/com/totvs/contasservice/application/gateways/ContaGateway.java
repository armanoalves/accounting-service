package com.totvs.contasservice.application.gateways;

import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.ContaFiltro;

import java.time.LocalDate;
import java.util.List;

public interface ContaGateway {
    Conta createConta(Conta conta);
    List<Conta> saveAll(List<Conta> contas);
    List<Conta> getAllConta(ContaFiltro contaFiltro, int page, int size);
    Conta findById(Long id);
    Double getValorTotalPagoPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
    Conta update(Long id, Conta conta);
    Conta updateSituacao(Long id, com.totvs.contasservice.domain.entity.Situacao situacao);
    void delete(Long id);
}
