package com.api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntidadRequestDto {
    private Long idEntidad;
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipo;
}
