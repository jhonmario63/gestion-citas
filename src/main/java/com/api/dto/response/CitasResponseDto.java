package com.api.dto.response;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
public class CitasResponseDto implements Serializable {
    private Long idCita;
    private AgendaResponseDto agenda;
    private Timestamp fechaHora;
    private String estado;
    private Timestamp fechaRegistro;
}
