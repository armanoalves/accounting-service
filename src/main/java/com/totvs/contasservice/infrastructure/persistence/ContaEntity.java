package com.totvs.contasservice.infrastructure.persistence;

import com.totvs.contasservice.domain.entity.Situacao;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Contas")
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private Double valor;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    public ContaEntity(LocalDate dataVencimento, LocalDate dataPagamento, Double valor, String descricao, Situacao situacao) {
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public ContaEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}
