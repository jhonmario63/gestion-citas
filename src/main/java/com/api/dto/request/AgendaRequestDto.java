package com.api.dto.request;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class AgendaRequestDto {
    private Long idAgenda;
    private UsuarioRequestDto usuario;
    private EntidadRequestDto entidad;
    private Timestamp fecha;
    private String tipoCita;
}
