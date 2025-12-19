package com.totvs.contasservice.domain.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String message) {
        super("Usuáro não encontrada");
    }
}
