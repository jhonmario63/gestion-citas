package com.api.config.security.aspect;

import com.api.config.UsuarioContextHolder;
import com.api.config.security.annotation.PermitirRoles;
import com.api.dto.AuthenticatedUser;
import com.api.utils.MensajesEnum;
import com.api.utils.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class PermitirRolesAspect {

    private final UsuarioContextHolder usuarioContextHolder;

    @Before("@annotation(permisos)")
    public void verificarPermisos(PermitirRoles permisos) {
        AuthenticatedUser usuario = usuarioContextHolder.getUsuario();
        boolean permitido = Arrays.stream(permisos.value())
                .anyMatch(rol -> rol == usuario.getTipoUsuario());
        if (!permitido) {
            throw new CustomException(MensajesEnum.ACCESO_DENEGADO.getMsg(), HttpStatus.FORBIDDEN);
        }
    }

}
