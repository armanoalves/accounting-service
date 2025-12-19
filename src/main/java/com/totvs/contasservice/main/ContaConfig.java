package com.totvs.contasservice.main;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.application.gateways.UsuarioGateway;
import com.totvs.contasservice.application.ports.CsvContaParser;
import com.totvs.contasservice.application.usecases.conta.*;
import com.totvs.contasservice.application.usecases.usuario.*;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.ContaDTOMapper;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.LoginDTOMapper;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.TotalPagoMapper;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.UsuarioDTOMapper;
import com.totvs.contasservice.infrastructure.gateways.ContaEntityMapper;
import com.totvs.contasservice.infrastructure.gateways.ContaRepositoryGateway;
import com.totvs.contasservice.infrastructure.gateways.UsuarioEntityMapper;
import com.totvs.contasservice.infrastructure.gateways.UsuarioRepositoryGateway;
import com.totvs.contasservice.infrastructure.persistence.ContaRepository;
import com.totvs.contasservice.infrastructure.persistence.UsuarioRepository;
import com.totvs.contasservice.infrastructure.security.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ContaConfig {
    @Bean
    CreateContaUseCase createContaUseCase(ContaGateway contaGateway) {
        return new CreateContaInteractor(contaGateway);
    }

    @Bean
    GetAllContaUseCase getAllContaUseCase(ContaGateway contaGateway) {
        return new GetAllContaInteractor((contaGateway));
    }

    @Bean
    GetByIdContaUseCase getByIdContaUseCase(ContaGateway contaGateway) {
        return new GetByIdContaInteractor(contaGateway);
    }

    @Bean
    UpdateContaUseCase updateContaUseCase(ContaGateway contaGateway) {
        return new UpdateContaInteractor(contaGateway);
    }

    @Bean
    DeleteContaUseCase deleteContaUseCase(ContaGateway contaGateway) {
        return new DeleteContaInteractor(contaGateway);
    }

    @Bean
    ImportContasFromCsvUseCase ImportCsvUseCase(ContaGateway contaGateway, CsvContaParser contaParser) {
        return new ImportContasFromCsvInteractor(contaParser, contaGateway);
    }

    @Bean
    CreateUsuarioUseCase createUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new CreateUsuarioInteractor(usuarioGateway);
    }

    @Bean
    GetAllUsuarioUseCase getAllUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new GetAllUsuarioInteractor(usuarioGateway);
    }

    @Bean
    LoginUseCase loginUseCase(UsuarioGateway usuarioGateway) {
        return new LoginInteractor(usuarioGateway);
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
    UsuarioGateway usuarioGateway(UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            UsuarioEntityMapper usuarioEntityMapper,
            AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService) {
        return new UsuarioRepositoryGateway(usuarioRepository, passwordEncoder, usuarioEntityMapper,
                authenticationManager, jwtTokenService);
    }

    @Bean
    ContaEntityMapper contaEntityMapper() {
        return new ContaEntityMapper();
    }

    @Bean
    UsuarioEntityMapper usuarioEntityMapper() {
        return new UsuarioEntityMapper();
    }

    @Bean
    ContaDTOMapper contaDTOMapper() {
        return new ContaDTOMapper();
    }

    @Bean
    UsuarioDTOMapper usuarioDTOMapper() {
        return new UsuarioDTOMapper();
    }

    @Bean
    LoginDTOMapper loginDTOMapper() {
        return new LoginDTOMapper();
    }

    @Bean
    public TotalPagoMapper totalPagoMapper() {
        return new TotalPagoMapper();
    }
}
