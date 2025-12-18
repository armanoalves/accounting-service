package com.totvs.contasservice.infrastructure.gateways;

import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.infrastructure.persistence.ContaEntity;

public class ContaEntityMapper {
    ContaEntity toEntity(Conta contaDomainObj) {
        return new ContaEntity(
                contaDomainObj.dataVencimento(),
                contaDomainObj.dataPagamento(),
                contaDomainObj.valor(),
                contaDomainObj.descricao(),
                contaDomainObj.situacao()
            );
    }

    Conta toDomainObj(ContaEntity contaEntity) {
        return new Conta(
                contaEntity.getId(),
                contaEntity.getDataVencimento(),
                contaEntity.getDataPagamento(),
                contaEntity.getValor(),
                contaEntity.getDescricao(),
                contaEntity.getSituacao()
        );
    }
}
