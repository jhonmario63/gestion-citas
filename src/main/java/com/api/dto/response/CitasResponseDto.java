package com.api.dto.response;


import com.api.model.enums.EstadoCitaEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Builder
public class CitasResponseDto implements Serializable {
    private Long idCita;
    private Time hora;
    private EstadoCitaEnum estado;
    private Timestamp fechaRegistro;
    private AgendaResponseDto agenda;
}