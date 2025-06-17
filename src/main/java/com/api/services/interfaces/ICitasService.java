package com.api.services.interfaces;

import com.api.dto.AuthenticatedUser;
import com.api.dto.request.CitasRequestDto;
import com.api.dto.response.CitasResponseDto;
import com.api.utils.exceptions.CustomException;

import java.sql.Date;
import java.util.List;

public interface ICitasService {
    void registrarCita(CitasRequestDto citasRequestDto) throws CustomException;

    List<CitasResponseDto> listarCitas(Date fecha) throws CustomException;

    void agendarCitaUsuario(Long idCita, AuthenticatedUser user) throws CustomException;
}
