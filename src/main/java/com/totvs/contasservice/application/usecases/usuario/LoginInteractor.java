package com.totvs.contasservice.application.usecases.usuario;

import com.totvs.contasservice.application.gateways.UsuarioGateway;

public class LoginInteractor {

    private final UsuarioGateway usuarioGateway;

    public LoginInteractor(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public String login(String email, String senha) {
        return usuarioGateway.login(email, senha);
    }
}
