package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.domain.entity.Conta;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImportContasFromCsvUseCase {
    List<Conta> importCsv(MultipartFile file);
}
