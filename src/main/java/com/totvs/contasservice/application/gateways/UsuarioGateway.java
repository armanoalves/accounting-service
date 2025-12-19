package com.totvs.contasservice.application.gateways;

import com.totvs.contasservice.domain.entity.Usuario;

import java.util.List;

public interface UsuarioGateway {
    Usuario createUsuario(Usuario usuario);
    String login(String email, String senha);
    List<Usuario> getAllUsuario(int page, int size);
}
