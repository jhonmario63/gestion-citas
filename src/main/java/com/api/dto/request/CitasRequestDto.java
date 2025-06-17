package com.api.dto.request;


import com.api.dto.response.AgendaResponseDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Builder
public class CitasRequestDto implements Serializable {
    private Long idCita;
    private AgendaResponseDto agenda;
    private Time hora;
    private Timestamp fechaRegistro;
}
