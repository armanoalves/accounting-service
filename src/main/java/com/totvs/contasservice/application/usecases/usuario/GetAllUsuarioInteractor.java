package com.totvs.contasservice.application.usecases.usuario;

import com.totvs.contasservice.application.gateways.UsuarioGateway;
import com.totvs.contasservice.domain.entity.Usuario;

import java.util.List;

public class GetAllUsuarioInteractor {
    private final UsuarioGateway usuarioGateway;

    public GetAllUsuarioInteractor(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public List<Usuario> getAllUsuario(int page, int size) {
        return usuarioGateway.getAllUsuario(page, size);
    }
}
