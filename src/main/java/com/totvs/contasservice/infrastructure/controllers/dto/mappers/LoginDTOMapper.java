package com.totvs.contasservice.infrastructure.controllers.dto.mappers;

import com.totvs.contasservice.infrastructure.controllers.dto.LoginResponse;

public class LoginDTOMapper {
    public LoginResponse toResponse(String token) {
        return new LoginResponse(
                token
        );
    }
}
