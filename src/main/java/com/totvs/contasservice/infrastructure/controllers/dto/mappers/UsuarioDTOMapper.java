package com.totvs.contasservice.infrastructure.controllers.dto.mappers;

import com.totvs.contasservice.domain.entity.Usuario;
import com.totvs.contasservice.infrastructure.controllers.dto.UsuarioRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.UsuarioResponse;

public class UsuarioDTOMapper {
    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }

    public Usuario toUsuario(UsuarioRequest request) {
        return new Usuario(
                null,
                request.email(),
                request.senha(),
                request.role()
        );
    }
}
