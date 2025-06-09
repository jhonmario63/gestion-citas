package com.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UsuarioResponseDto {
    private Long idUsuario;
    private String nombre;
    private String tipoDocumento;
    private String numDocumento;
    private String email;
    private String telefono;
    private Timestamp fechaRegistro;
}
