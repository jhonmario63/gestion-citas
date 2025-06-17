package com.api.services.interfaces;

import com.api.dto.AuthenticatedUser;
import com.api.dto.request.AgendaRequestDto;
import com.api.dto.response.AgendaResponseDto;
import com.api.utils.exceptions.CustomException;

import java.sql.Date;
import java.util.List;

public interface IAgendaService {
    void registrarAgenda(AgendaRequestDto agendaRequestDto, AuthenticatedUser user) throws CustomException;

    List<AgendaResponseDto> listarAgendas(Date fechaInicial, Date fechaFinal, AuthenticatedUser user) throws CustomException;
}
