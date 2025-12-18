package com.totvs.contasservice.infrastructure.controllers;

import com.totvs.contasservice.application.usecases.*;
import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.ContaFiltro;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaResponse;
import com.totvs.contasservice.infrastructure.controllers.dto.TotalPagoResponse;
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

    private final CreateContaInterector createContaInterector;
    private final GetAllContaInterector getAllContaInterector;
    private final GetByIdContaInterector getByIdContaInterector;
    private final UpdateContaInterector updateContaInterector;
    private final DeleteContaInterector deleteContaInterector;
    private final ImportContasFromCsvInteractor importContasFromCsvInteractor;
    private final GetValorTotalPagoPorPeriodoInteractor getValorTotalPagoPorPeriodoInteractor;
    private final ContaDTOMapper contaDTOMapper;
    private final TotalPagoMapper totalPagoMapper;

    public ContaController(CreateContaInterector createContaInterector,
                           ContaDTOMapper contaDTOMapper,
                           TotalPagoMapper totalPagoMapper,
                           GetAllContaInterector getAllContaInterector,
                           GetByIdContaInterector getByIdContaInterector,
                           UpdateContaInterector updateContaInterector,
                           DeleteContaInterector deleteContaInterector,
                           ImportContasFromCsvInteractor importContasFromCsvInteractor,
                           GetValorTotalPagoPorPeriodoInteractor getValorTotalPagoPorPeriodoInteractor) {
        this.createContaInterector = createContaInterector;
        this.getAllContaInterector = getAllContaInterector;
        this.getByIdContaInterector = getByIdContaInterector;
        this.updateContaInterector = updateContaInterector;
        this.deleteContaInterector = deleteContaInterector;
        this.importContasFromCsvInteractor = importContasFromCsvInteractor;
        this.getValorTotalPagoPorPeriodoInteractor = getValorTotalPagoPorPeriodoInteractor;
        this.contaDTOMapper = contaDTOMapper;
        this.totalPagoMapper = totalPagoMapper;
    }

    @PostMapping
    ResponseEntity<ContaResponse> create(@RequestBody ContaRequest request) {
        Conta contaBusinessObj = contaDTOMapper.toConta(request);
        Conta conta = createContaInterector.createConta(contaBusinessObj);
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

        List<Conta> contasBusinnesObj = getAllContaInterector
                .getAllConta(
                        filtro,
                        pageable.getPageNumber(),
                        pageable.getPageSize());

        Page<ContaResponse> contas = new PageImpl<>(
                contasBusinnesObj.stream()
                        .map(contaDTOMapper::toResponse)
                        .toList()
        );
        return ResponseEntity.status(HttpStatus.OK).body(contas);
    }

    @GetMapping("/{id}")
    ResponseEntity<ContaResponse> findById(@PathVariable Long id) {
        Conta contaBusinnesObj = getByIdContaInterector.findById(id);
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
        Conta conta = updateContaInterector.update(id, contaBusinnesObj);
        return ResponseEntity.status(HttpStatus.OK).body(contaDTOMapper.toResponse(conta));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteContaInterector.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
