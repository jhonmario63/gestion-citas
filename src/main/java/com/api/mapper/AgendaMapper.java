package com.api.mapper;

import com.api.dto.response.AgendaResponseDto;
import com.api.model.entities.AgendaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendaMapper {

    public AgendaResponseDto toDto(AgendaEntity entity) {
        return AgendaResponseDto.builder()
                .idAgenda(entity.getIdAgenda())
                .nombreAgenda(entity.getNombreAgenda())
                .fechaAgenda(entity.getFechaAgenda())
                .tipoAgenda(entity.getTipoAgenda())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }

}
