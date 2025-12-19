package com.totvs.contasservice.infrastructure.controllers.dto;

public record LoginRequest(
        String email,
        String senha
) {
}
