package com.totvs.contasservice.infrastructure.controllers.dto;

import com.totvs.contasservice.domain.entity.Role;

public record UsuarioRequest(
        String email,
        String senha,
        Role role
) {
}
