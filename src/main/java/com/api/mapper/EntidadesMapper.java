package com.api.mapper;

import com.api.dto.response.EntidadResponseDto;
import com.api.model.entities.EntidadesEntity;
import org.springframework.stereotype.Component;

@Component
public class EntidadesMapper {

    public EntidadResponseDto toDto(EntidadesEntity entity) {
        return EntidadResponseDto.builder()
                .idEntidad(entity.getIdEntidad())
                .nitEntidad(entity.getNitEntidad())
                .nombre(entity.getNombre())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .tipo(entity.getTipo())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
