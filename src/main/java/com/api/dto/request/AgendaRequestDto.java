package com.api.dto.request;

import com.api.model.enums.TipoAgendaEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
public class AgendaRequestDto {
    private Long idAgenda;
    private String nombreAgenda;
    private EntidadRequestDto entidad;
    private TipoAgendaEnum tipoAgenda;
    private Date fechaAgenda;
    private String tipoCita;
}
