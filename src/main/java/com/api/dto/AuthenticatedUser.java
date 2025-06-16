package com.api.dto;

import com.api.model.enums.TipoUsuarioEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class AuthenticatedUser {
    private Long idUsuario;
    private String nombre;
    private String tipoDocumento;
    private String numDocumento;
    private String email;
    private String telefono;
    private Timestamp fechaRegistro;
    private TipoUsuarioEnum tipoUsuario;
}
