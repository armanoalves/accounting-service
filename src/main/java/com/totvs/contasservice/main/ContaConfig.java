package com.totvs.contasservice.main;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.application.ports.CsvContaParser;
import com.totvs.contasservice.application.usecases.*;
import com.totvs.contasservice.infrastructure.controllers.ContaDTOMapper;
import com.totvs.contasservice.infrastructure.controllers.TotalPagoMapper;
import com.totvs.contasservice.infrastructure.gateways.ContaEntityMapper;
import com.totvs.contasservice.infrastructure.gateways.ContaRepositoryGateway;
import com.totvs.contasservice.infrastructure.persistence.ContaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContaConfig {
    @Bean
    CreateContaInterector createUseCase(ContaGateway contaGateway) {
        return new CreateContaInterector(contaGateway);
    }

    @Bean
    GetAllContaInterector getAllUseCase(ContaGateway contaGateway) {
        return new GetAllContaInterector((contaGateway));
    }

    @Bean
    GetByIdContaInterector getByIdUseCase(ContaGateway contaGateway) {
        return new GetByIdContaInterector(contaGateway);
    }

    @Bean
    UpdateContaInterector updateUseCase(ContaGateway contaGateway) {
        return new UpdateContaInterector(contaGateway);
    }

    @Bean
    DeleteContaInterector deleteUseCase(ContaGateway contaGateway) {
        return new DeleteContaInterector(contaGateway);
    }

    @Bean
    ImportContasFromCsvInteractor ImportCsvUseCase(ContaGateway contaGateway, CsvContaParser contaParser) {
        return new ImportContasFromCsvInteractor(contaParser, contaGateway);
    }

    @Bean
    GetValorTotalPagoPorPeriodoInteractor ValorTotalUseCase(ContaGateway contaGateway) {
        return new GetValorTotalPagoPorPeriodoInteractor(contaGateway);
    }

    @Bean
    ContaGateway contaGateway(ContaRepository contaRepository, ContaEntityMapper contaEntityMapper) {
        return new ContaRepositoryGateway(contaRepository, contaEntityMapper);
    }

    @Bean
    ContaEntityMapper contaEntityMapper() {
        return new ContaEntityMapper();
    }

    @Bean
    ContaDTOMapper contaDTOMapper() {
        return new ContaDTOMapper();
    }

    @Bean
    public TotalPagoMapper totalPagoMapper() {
        return new TotalPagoMapper();
    }
}
