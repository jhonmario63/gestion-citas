package com.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class EntidadResponseDto {
    private Long idEntidad;
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipo;
    private Timestamp fechaRegistro;
}
