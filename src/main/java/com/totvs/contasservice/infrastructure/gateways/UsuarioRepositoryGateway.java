package com.totvs.contasservice.infrastructure.gateways;

import com.totvs.contasservice.infrastructure.controllers.dto.LoginResponse;
import com.totvs.contasservice.application.gateways.UsuarioGateway;
import com.totvs.contasservice.domain.entity.Usuario;
import com.totvs.contasservice.infrastructure.persistence.UsuarioEntity;
import com.totvs.contasservice.infrastructure.persistence.UsuarioRepository;
import com.totvs.contasservice.infrastructure.security.JwtTokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UsuarioRepositoryGateway implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioEntityMapper usuarioEntityMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public UsuarioRepositoryGateway(UsuarioRepository usuarioRepository,
                                    PasswordEncoder passwordEncoder,
                                    UsuarioEntityMapper usuarioEntityMapper,
                                    AuthenticationManager authenticationManager,
                                    JwtTokenService jwtTokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioEntityMapper = usuarioEntityMapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioEntityMapper.toEntity(usuario);
        var password = passwordEncoder.encode(usuarioEntity.getPassword());
        usuarioEntity.setSenha(password);
        UsuarioEntity save = usuarioRepository.save(usuarioEntity);
        return usuarioEntityMapper.toDomain(save);
    }

    @Override
    public String login(String email, String senha) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(email, senha);
        var authentication = authenticationManager.authenticate(authenticationToken);
        return jwtTokenService.gerarToken((UsuarioEntity) authentication.getPrincipal());
    }

    @Override
    public List<Usuario> getAllUsuario(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<UsuarioEntity> pageResult = usuarioRepository.findAll(pageRequest);
        return pageResult.stream()
                .map(usuarioEntityMapper::toDomain)
                .toList();
    }
}
