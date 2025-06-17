package com.api.mapper;

import com.api.dto.response.CitasResponseDto;
import com.api.model.entities.CitasEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitasMapper {

    private final AgendaMapper agendaMapper;

    public CitasResponseDto toDto(CitasEntity entity) {
        return CitasResponseDto.builder()
                .idCita(entity.getIdCita())
                .hora(entity.getHora())
                .estado(entity.getEstado())
                .fechaRegistro(entity.getFechaRegistro())
                .agenda(agendaMapper.toDto(entity.getAgenda()))
                .build();
    }

}
