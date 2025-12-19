package com.totvs.contasservice.infrastructure.controllers;

import com.totvs.contasservice.application.usecases.conta.*;
import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.ContaFiltro;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaRequest;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("contas")
public class ContaController {

    private final CreateContaInteractor createContaInteractor;
    private final GetAllContaInteractor getAllContaInteractor;
    private final GetByIdContaInteractor getByIdContaInteractor;
    private final UpdateContaInteractor updateContaInteractor;
    private final DeleteContaInteractor deleteContaInteractor;
    private final ImportContasFromCsvInteractor importContasFromCsvInteractor;
    private final GetValorTotalPagoPorPeriodoInteractor getValorTotalPagoPorPeriodoInteractor;
    private final ContaDTOMapper contaDTOMapper;
    private final TotalPagoMapper totalPagoMapper;

    public ContaController(CreateContaInteractor createContaInteractor,
                           ContaDTOMapper contaDTOMapper,
                           TotalPagoMapper totalPagoMapper,
                           GetAllContaInteractor getAllContaInteractor,
                           GetByIdContaInteractor getByIdContaInteractor,
                           UpdateContaInteractor updateContaInteractor,
                           DeleteContaInteractor deleteContaInteractor,
                           ImportContasFromCsvInteractor importContasFromCsvInteractor,
                           GetValorTotalPagoPorPeriodoInteractor getValorTotalPagoPorPeriodoInteractor) {
        this.createContaInteractor = createContaInteractor;
        this.getAllContaInteractor = getAllContaInteractor;
        this.getByIdContaInteractor = getByIdContaInteractor;
        this.updateContaInteractor = updateContaInteractor;
        this.deleteContaInteractor = deleteContaInteractor;
        this.importContasFromCsvInteractor = importContasFromCsvInteractor;
        this.getValorTotalPagoPorPeriodoInteractor = getValorTotalPagoPorPeriodoInteractor;
        this.contaDTOMapper = contaDTOMapper;
        this.totalPagoMapper = totalPagoMapper;
    }

    @PostMapping
    ResponseEntity<ContaResponse> create(@RequestBody ContaRequest request) {
        Conta contaBusinessObj = contaDTOMapper.toConta(request);
        Conta conta = createContaInteractor.createConta(contaBusinessObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaDTOMapper.toResponse(conta));
    }

    @PostMapping("/import")
    public ResponseEntity<List<ContaResponse>> importCsv(@RequestParam("file") MultipartFile file) {
        List<Conta> contasCriadas = importContasFromCsvInteractor.importCsv(file);

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

        List<Conta> contasBusinessObj = getAllContaInteractor
                .getAllConta(
                        filtro,
                        pageable.getPageNumber(),
                        pageable.getPageSize());

        Page<ContaResponse> contas = new PageImpl<>(
                contasBusinessObj.stream()
                        .map(contaDTOMapper::toResponse)
                        .toList()
        );
        return ResponseEntity.status(HttpStatus.OK).body(contas);
    }

    @GetMapping("/{id}")
    ResponseEntity<ContaResponse> findById(@PathVariable Long id) {
        Conta contaBusinnesObj = getByIdContaInteractor.findById(id);
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

        Double total = getValorTotalPagoPorPeriodoInteractor.execute(inicio, fim);
        return ResponseEntity.status(HttpStatus.OK).body(totalPagoMapper.toResponse(inicio, fim, total));
    }


    @PutMapping("/{id}")
    ResponseEntity<ContaResponse> update(@PathVariable Long id, @RequestBody ContaRequest request) {
        Conta contaBusinnesObj = contaDTOMapper.toConta(request);
        Conta conta = updateContaInteractor.update(id, contaBusinnesObj);
        return ResponseEntity.status(HttpStatus.OK).body(contaDTOMapper.toResponse(conta));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteContaInteractor.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
