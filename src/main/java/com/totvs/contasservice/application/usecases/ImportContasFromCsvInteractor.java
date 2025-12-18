package com.totvs.contasservice.application.usecases;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.application.ports.CsvContaParser;
import com.totvs.contasservice.domain.entity.Conta;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImportContasFromCsvInteractor {

    private final CsvContaParser parser;
    private final ContaGateway contaGateway;

    public ImportContasFromCsvInteractor(
            CsvContaParser parser,
            ContaGateway gateway) {
        this.parser = parser;
        this.contaGateway = gateway;
    }

    public List<Conta> importCsv(MultipartFile file) {
        List<Conta> contas = parser.parse(file);
        return contaGateway.saveAll(contas);
    }
}