package com.api.services.interfaces;

import com.api.dto.request.EntidadRequestDto;
import com.api.dto.response.EntidadResponseDto;
import com.api.utils.exceptions.CustomException;

public interface IEntidadesService {
    EntidadResponseDto registrarEntidad(EntidadRequestDto entidadRequestDto) throws CustomException;
}
