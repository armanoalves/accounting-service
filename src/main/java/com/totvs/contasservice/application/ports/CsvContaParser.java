package com.totvs.contasservice.application.ports;

import com.totvs.contasservice.domain.entity.Conta;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvContaParser {
    List<Conta> parse(MultipartFile file);
}
