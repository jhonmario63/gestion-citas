package com.api.config;

import com.api.dto.AuthenticatedUser;
import com.api.mapper.UsuarioMapper;
import com.api.model.entities.UsuariosEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioContextHolder {

    private final UsuarioMapper usuarioMapper;

    public AuthenticatedUser getUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UsuariosEntity) {
            return usuarioMapper.toAuthenticatedUser((UsuariosEntity) principal);
        }
        return null;
    }

}
