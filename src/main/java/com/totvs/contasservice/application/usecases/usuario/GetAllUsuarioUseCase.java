package com.totvs.contasservice.application.usecases.usuario;

import com.totvs.contasservice.domain.entity.Usuario;
import java.util.List;

public interface GetAllUsuarioUseCase {
    List<Usuario> getAllUsuario(int page, int size);
}
