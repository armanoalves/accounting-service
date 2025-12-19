package com.totvs.contasservice.application.usecases.usuario;

import com.totvs.contasservice.domain.entity.Usuario;

public interface CreateUsuarioUseCase {
    Usuario createUsuario(Usuario usuario);
}
