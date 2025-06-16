package com.api.mapper;


import com.api.dto.AuthenticatedUser;
import com.api.dto.response.UsuarioResponseDto;
import com.api.model.entities.UsuariosEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponseDto toDto(UsuariosEntity entity) {
        return UsuarioResponseDto.builder()
                .idUsuario(entity.getIdUsuario())
                .nombre(entity.getNombre())
                .tipoDocumento(entity.getTipoDocumento())
                .numDocumento(entity.getNumDocumento())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .fechaRegistro(entity.getFechaRegistro())
                .tipoUsuario(entity.getTipoUsuario())
                .build();
    }

    public AuthenticatedUser toAuthenticatedUser(UsuariosEntity entity) {
        return AuthenticatedUser.builder()
                .idUsuario(entity.getIdUsuario())
                .nombre(entity.getNombre())
                .tipoDocumento(entity.getTipoDocumento())
                .numDocumento(entity.getNumDocumento())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .fechaRegistro(entity.getFechaRegistro())
                .tipoUsuario(entity.getTipoUsuario()).build();
    }
}
