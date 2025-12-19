package com.totvs.contasservice.infrastructure.gateways;

import com.totvs.contasservice.domain.entity.Usuario;
import com.totvs.contasservice.infrastructure.persistence.UsuarioEntity;

public class UsuarioEntityMapper {
    UsuarioEntity toEntity(Usuario usuarioDomainObj) {
        return new UsuarioEntity(
                usuarioDomainObj.getEmail(),
                usuarioDomainObj.getSenha(),
                usuarioDomainObj.getRole()
        );
    }

    Usuario toDomain(UsuarioEntity usuarioEntity) {
        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha(),
                usuarioEntity.getrole()
        );
    }
}
