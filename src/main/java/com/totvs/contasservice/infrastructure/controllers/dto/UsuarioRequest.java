package com.totvs.contasservice.infrastructure.controllers.dto;

import com.totvs.contasservice.domain.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
                @NotBlank(message = "O email não pode estar vazio") @Email(message = "Email inválido") String email,
                @NotBlank(message = "A senha não pode estar vazia") String senha,
                @NotNull(message = "A role não pode ser nula") Role role) {
}
