package com.api.mapper;

import com.api.dto.response.AgendaResponseDto;
import com.api.model.entities.AgendaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendaMapper {

    private final UsuarioMapper usuarioMapper;
    private final EntidadesMapper entidadesMapper;

    public AgendaResponseDto toDto(AgendaEntity entity) {
        return AgendaResponseDto.builder()
                .idAgenda(entity.getIdAgenda())
                .fecha(entity.getFecha())
                .tipoCita(entity.getTipoCita())
                .fechaRegistro(entity.getFechaRegistro())
                .usuario(usuarioMapper.toDto(entity.getUsuario()))
                .entidad(entidadesMapper.toDto(entity.getEntidad()))
                .build();
    }

}
