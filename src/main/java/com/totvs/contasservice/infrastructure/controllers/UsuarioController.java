package com.totvs.contasservice.infrastructure.controllers;

import com.totvs.contasservice.infrastructure.controllers.dto.LoginRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.LoginResponse;
import com.totvs.contasservice.application.usecases.usuario.CreateUsuarioUseCase;
import com.totvs.contasservice.application.usecases.usuario.GetAllUsuarioUseCase;
import com.totvs.contasservice.application.usecases.usuario.LoginUseCase;
import com.totvs.contasservice.domain.entity.Usuario;
import com.totvs.contasservice.infrastructure.controllers.dto.UsuarioRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.UsuarioResponse;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.LoginDTOMapper;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.UsuarioDTOMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final CreateUsuarioUseCase createUsuarioUseCase;
    private final GetAllUsuarioUseCase getAllUsuarioUseCase;
    private final LoginUseCase loginUseCase;
    private final UsuarioDTOMapper usuarioDTOMapper;
    private final LoginDTOMapper loginDTOMapper;

    public UsuarioController(CreateUsuarioUseCase createUsuarioUseCase,
            GetAllUsuarioUseCase getAllUsuarioUseCase,
            LoginUseCase loginUseCase,
            UsuarioDTOMapper usuarioDTOMapper,
            LoginDTOMapper loginDTOMapper) {
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.getAllUsuarioUseCase = getAllUsuarioUseCase;
        this.loginUseCase = loginUseCase;
        this.usuarioDTOMapper = usuarioDTOMapper;
        this.loginDTOMapper = loginDTOMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioResponse> create(@RequestBody @Valid UsuarioRequest request) {
        Usuario usuarioBusinessObj = usuarioDTOMapper.toUsuario(request);
        Usuario usuario = createUsuarioUseCase.createUsuario(usuarioBusinessObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTOMapper.toResponse(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        var response = loginUseCase.login(request.email(), request.senha());
        return ResponseEntity.status(HttpStatus.OK).body(loginDTOMapper.toResponse(response));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> findAll(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        List<Usuario> usuariosBusinessObj = getAllUsuarioUseCase
                .getAllUsuario(
                        pageable.getPageNumber(),
                        pageable.getPageSize());

        Page<UsuarioResponse> usuarios = new PageImpl<>(
                usuariosBusinessObj.stream()
                        .map(usuarioDTOMapper::toResponse)
                        .toList());
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
}
