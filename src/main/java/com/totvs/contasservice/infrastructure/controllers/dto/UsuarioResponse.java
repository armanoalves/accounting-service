package com.totvs.contasservice.infrastructure.controllers.dto;

import com.totvs.contasservice.domain.entity.Role;

public record UsuarioResponse(
        Long id,
        String email,
        Role role
) {
}
