package com.api.services.service;

import com.api.dto.request.EntidadRequestDto;
import com.api.dto.response.EntidadResponseDto;
import com.api.services.interfaces.IEntidadesService;
import com.api.utils.exceptions.CustomException;
import org.springframework.stereotype.Service;

@Service
public class EntidadesService implements IEntidadesService {
    @Override
    public EntidadResponseDto registrarEntidad(EntidadRequestDto entidadRequestDto) throws CustomException {
        try {

        } catch (Exception e) {
            log.error(e.getMessage(), this.getClass().getName());
        }



        return null;
    }
}
