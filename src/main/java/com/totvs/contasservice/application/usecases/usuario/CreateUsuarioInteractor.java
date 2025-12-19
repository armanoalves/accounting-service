package com.totvs.contasservice.application.usecases.usuario;

import com.totvs.contasservice.application.gateways.UsuarioGateway;
import com.totvs.contasservice.domain.entity.Usuario;

public class CreateUsuarioInteractor implements CreateUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;

    public CreateUsuarioInteractor(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioGateway.createUsuario(usuario);
    }
}
