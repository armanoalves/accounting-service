package com.totvs.contasservice.domain.exceptions;

public class ContaNaoEncontradaException extends RuntimeException {
    public ContaNaoEncontradaException() {
        super("Conta não encontrada");
    }
}
