package com.totvs.contasservice.infrastructure.controllers;

import com.totvs.contasservice.application.usecases.conta.*;
import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.ContaFiltro;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.UpdateSituacaoRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaResponse;
import com.totvs.contasservice.infrastructure.controllers.dto.TotalPagoResponse;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.ContaDTOMapper;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.TotalPagoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("contas")
public class ContaController {

    private final CreateContaUseCase createContaUseCase;
    private final GetAllContaUseCase getAllContaUseCase;
    private final GetByIdContaUseCase getByIdContaUseCase;
    private final UpdateContaUseCase updateContaUseCase;
    private final DeleteContaUseCase deleteContaUseCase;
    private final ImportContasFromCsvUseCase importContasFromCsvUseCase;
    private final GetValorTotalPagoPorPeriodoUseCase getValorTotalPagoPorPeriodoUseCase;
    private final UpdateSituacaoContaUseCase updateSituacaoContaUseCase;
    private final ContaDTOMapper contaDTOMapper;
    private final TotalPagoMapper totalPagoMapper;

    public ContaController(CreateContaUseCase createContaUseCase,
            GetAllContaUseCase getAllContaUseCase,
            GetByIdContaUseCase getByIdContaUseCase,
            UpdateContaUseCase updateContaUseCase,
            DeleteContaUseCase deleteContaUseCase,
            ImportContasFromCsvUseCase importContasFromCsvUseCase,
            GetValorTotalPagoPorPeriodoUseCase getValorTotalPagoPorPeriodoUseCase,
            UpdateSituacaoContaUseCase updateSituacaoContaUseCase,
            ContaDTOMapper contaDTOMapper, TotalPagoMapper totalPagoMapper) {
        this.createContaUseCase = createContaUseCase;
        this.getAllContaUseCase = getAllContaUseCase;
        this.getByIdContaUseCase = getByIdContaUseCase;
        this.updateContaUseCase = updateContaUseCase;
        this.deleteContaUseCase = deleteContaUseCase;
        this.importContasFromCsvUseCase = importContasFromCsvUseCase;
        this.getValorTotalPagoPorPeriodoUseCase = getValorTotalPagoPorPeriodoUseCase;
        this.updateSituacaoContaUseCase = updateSituacaoContaUseCase;
        this.contaDTOMapper = contaDTOMapper;
        this.totalPagoMapper = totalPagoMapper;
    }

    @PostMapping
    ResponseEntity<ContaResponse> create(@RequestBody @Valid ContaRequest request) {
        Conta contaBusinessObj = contaDTOMapper.toConta(request);
        Conta conta = createContaUseCase.createConta(contaBusinessObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaDTOMapper.toResponse(conta));
    }

    @PostMapping("/import")
    public ResponseEntity<List<ContaResponse>> importCsv(@RequestParam("file") MultipartFile file) {
        List<Conta> contasCriadas = importContasFromCsvUseCase.importCsv(file);

        List<ContaResponse> response = contasCriadas.stream()
                .map(contaDTOMapper::toResponse)
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    ResponseEntity<Page<ContaResponse>> findAll(
            @RequestParam(required = false) LocalDate dataVencimento,
            @RequestParam(required = false) String descricao,
            @PageableDefault(page = 0, size = 5) Pageable pageable) {

        ContaFiltro filtro = new ContaFiltro(dataVencimento, descricao);

        List<Conta> contasBusinessObj = getAllContaUseCase
                .getAllConta(
                        filtro,
                        pageable.getPageNumber(),
                        pageable.getPageSize());

        Page<ContaResponse> contas = new PageImpl<>(
                contasBusinessObj.stream()
                        .map(contaDTOMapper::toResponse)
                        .toList());
        return ResponseEntity.status(HttpStatus.OK).body(contas);
    }

    @GetMapping("/{id}")
    ResponseEntity<ContaResponse> findById(@PathVariable Long id) {
        Conta contaBusinnesObj = getByIdContaUseCase.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(contaDTOMapper.toResponse(contaBusinnesObj));

    }

    @GetMapping("/total-pago")
    public ResponseEntity<TotalPagoResponse> getValorTotalPago(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        if (inicio == null || fim == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (inicio.isAfter(fim)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Double total = getValorTotalPagoPorPeriodoUseCase.execute(inicio, fim);
        return ResponseEntity.status(HttpStatus.OK).body(totalPagoMapper.toResponse(inicio, fim, total));
    }

    @PutMapping("/{id}")
    ResponseEntity<ContaResponse> update(@PathVariable Long id, @RequestBody @Valid ContaRequest request) {
        Conta contaBusinnesObj = contaDTOMapper.toConta(request);
        Conta conta = updateContaUseCase.updateConta(id, contaBusinnesObj);
        return ResponseEntity.status(HttpStatus.OK).body(contaDTOMapper.toResponse(conta));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteContaUseCase.deleteConta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/situacao")
    ResponseEntity<ContaResponse> updateSituacao(@PathVariable Long id,
            @RequestBody @Valid UpdateSituacaoRequest request) {
        Conta conta = updateSituacaoContaUseCase.updateSituacao(id, request.situacao());
        return ResponseEntity.status(HttpStatus.OK).body(contaDTOMapper.toResponse(conta));
    }

}
