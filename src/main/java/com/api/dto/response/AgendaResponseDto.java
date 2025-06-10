package com.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class AgendaResponseDto {
    private Long idAgenda;
    private UsuarioResponseDto usuario;
    private EntidadResponseDto entidad;
    private Timestamp fecha;
    private String tipoCita;
    private Timestamp fechaRegistro;
}
