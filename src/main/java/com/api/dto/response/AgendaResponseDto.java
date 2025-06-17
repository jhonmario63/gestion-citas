package com.api.dto.response;

import com.api.model.enums.TipoAgendaEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
public class AgendaResponseDto {
    private Long idAgenda;
    private String nombreAgenda;
    private TipoAgendaEnum tipoAgenda;
    private Date fechaAgenda;
    private Timestamp fechaRegistro;
}
