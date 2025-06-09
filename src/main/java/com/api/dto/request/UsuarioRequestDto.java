package com.api.dto.request;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UsuarioRequestDto {
    private Long idUsuario;
    private String nombre;
    private String tipoDocumento;
    private String numDocumento;
    private String email;
    private String telefono;
    private Timestamp fechaRegistro;
}
