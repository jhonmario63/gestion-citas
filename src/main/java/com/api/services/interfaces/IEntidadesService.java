package com.api.services.interfaces;

import com.api.dto.AuthenticatedUser;
import com.api.dto.request.EntidadRequestDto;
import com.api.dto.response.EntidadResponseDto;
import com.api.utils.exceptions.CustomException;

import java.util.List;

public interface IEntidadesService {
    void registrarEntidad(EntidadRequestDto entidadRequestDto, AuthenticatedUser user) throws CustomException;

    void actualizarEntidad(EntidadRequestDto entidadRequestDto) throws CustomException;

    List<EntidadResponseDto> listarEntidad() throws CustomException;
}
