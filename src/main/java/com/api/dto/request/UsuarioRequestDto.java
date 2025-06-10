package com.api.dto.request;

import com.api.model.enums.TipoUsuarioEnum;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UsuarioRequestDto {
    private Long idUsuario;
    private String nombre;
    private String tipoDocumento;
    private String numDocumento;
    private String email;
    private String password;
    private String telefono;
    private TipoUsuarioEnum tipoUsuario;
    private Timestamp fechaRegistro;
}
