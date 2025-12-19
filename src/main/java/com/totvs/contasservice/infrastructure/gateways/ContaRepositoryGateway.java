package com.totvs.contasservice.infrastructure.gateways;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.ContaFiltro;
import com.totvs.contasservice.domain.entity.Situacao;
import com.totvs.contasservice.domain.exceptions.ContaNaoEncontradaException;
import com.totvs.contasservice.infrastructure.persistence.ContaEntity;
import com.totvs.contasservice.infrastructure.persistence.ContaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class ContaRepositoryGateway implements ContaGateway {

    private final ContaRepository contaRepository;
    private final ContaEntityMapper contaEntityMapper;

    public ContaRepositoryGateway(ContaRepository contaRepository, ContaEntityMapper contaEntityMapper) {
        this.contaRepository = contaRepository;
        this.contaEntityMapper = contaEntityMapper;
    }

    @Override
    public Conta createConta(Conta contaDomainObj) {
        ContaEntity contaEntity = contaEntityMapper.toEntity(contaDomainObj);
        ContaEntity saveObj = contaRepository.save(contaEntity);
        return contaEntityMapper.toDomainObj(saveObj);
    }

    @Override
    public List<Conta> saveAll(List<Conta> contas) {
        List<ContaEntity> entities = contas.stream()
                .map(contaEntityMapper::toEntity)
                .toList();

        List<ContaEntity> savedEntities = contaRepository.saveAll(entities);

        return savedEntities.stream()
                .map(contaEntityMapper::toDomainObj)
                .toList();
    }

    @Override
    public List<Conta> getAllConta(ContaFiltro filtro, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Specification<ContaEntity> spec = (root, query, cb) -> cb.conjunction();

        if (filtro.dataVencimento() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dataVencimento"), filtro.dataVencimento()));
        }

        if (filtro.descricao() != null && !filtro.descricao().isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(
                    cb.lower(root.get("descricao")),
                    "%" + filtro.descricao().toLowerCase() + "%"));
        }
        Page<ContaEntity> pageResult = contaRepository.findAll(spec, pageRequest);
        return pageResult.stream()
                .map(contaEntityMapper::toDomainObj)
                .toList();
    }

    @Override
    public Conta findById(Long id) {
        ContaEntity contaEntity = contaRepository.findById(id).orElseThrow(ContaNaoEncontradaException::new);
        return contaEntityMapper.toDomainObj(contaEntity);
    }

    @Override
    public Double getValorTotalPagoPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("dataInicio não pode ser maior que dataFim");
        }
        return contaRepository.findTotalPagoPorPeriodo(Situacao.PAGO, dataInicio, dataFim);
    }

    @Override
    public Conta update(Long id, Conta conta) {
        ContaEntity contaEntity = contaRepository.findById(id).orElseThrow(ContaNaoEncontradaException::new);

        contaEntity.setDataVencimento(conta.getDataVencimento());
        contaEntity.setDataPagamento(conta.getDataPagamento());
        contaEntity.setValor(conta.getValor());
        contaEntity.setDescricao(conta.getDescricao());
        contaEntity.setSituacao(conta.getSituacao());

        ContaEntity updatedConta = contaRepository.save(contaEntity);

        return contaEntityMapper.toDomainObj(updatedConta);
    }

    @Override
    public Conta updateSituacao(Long id, Situacao situacao) {
        ContaEntity contaEntity = contaRepository.findById(id).orElseThrow(ContaNaoEncontradaException::new);
        contaEntity.setSituacao(situacao);
        ContaEntity updatedConta = contaRepository.save(contaEntity);
        return contaEntityMapper.toDomainObj(updatedConta);
    }

    @Override
    public void delete(Long id) {
        ContaEntity contaEntity = contaRepository.findById(id).orElseThrow(ContaNaoEncontradaException::new);
        contaRepository.delete(contaEntity);
    }

}
