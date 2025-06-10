package com.api.dto.response;

import com.api.model.enums.TipoUsuarioEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class UsuarioResponseDto {
    private Long idUsuario;
    private String nombre;
    private String tipoDocumento;
    private String numDocumento;
    private String email;
    private String telefono;
    private String token;
    private List<AgendaResponseDto> agendas;
    private Timestamp fechaRegistro;
    private TipoUsuarioEnum tipoUsuario;
}
