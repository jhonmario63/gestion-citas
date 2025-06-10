package com.api.services.interfaces;

import com.api.dto.request.CitasRequestDto;
import com.api.dto.response.CitasResponseDto;
import com.api.utils.exceptions.CustomException;

import java.sql.Timestamp;
import java.util.List;

public interface ICitasService {
    void registrarCita(CitasRequestDto citasRequestDto) throws CustomException;

    List<CitasResponseDto> listarCitas(Timestamp fechaInicial, Timestamp fechaFinal) throws CustomException;
}
