package com.totvs.contasservice.infrastructure.csv;

import com.totvs.contasservice.application.ports.CsvContaParser;
import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.Situacao;
import com.totvs.contasservice.domain.exceptions.ProcessamentoCsvException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

@Component
public class CsvContaParserImpl implements CsvContaParser {

    @Override
    public List<Conta> parse(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream()))) {

            return reader.lines()
                    .skip(1) // pula cabeçalho
                    .map(this::parseLine)
                    .toList();

        } catch (Exception e) {
            throw new ProcessamentoCsvException("Erro ao processar CSV: " + e.getMessage());
        }
    }

    private Conta parseLine(String line) {
        String[] col = line.split(",");

        return new Conta(
                null,
                LocalDate.parse(col[0]),
                col[1].isBlank() ? null : LocalDate.parse(col[1]),
                Double.valueOf(col[2]),
                col[3],
                Situacao.valueOf(col[4])
        );
    }
}
