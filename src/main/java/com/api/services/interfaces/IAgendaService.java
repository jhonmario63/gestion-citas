package com.api.services.interfaces;

import com.api.dto.AuthenticatedUser;
import com.api.dto.request.AgendaRequestDto;
import com.api.dto.response.AgendaResponseDto;
import com.api.utils.exceptions.CustomException;

import java.sql.Timestamp;
import java.util.List;

public interface IAgendaService {
    void registrarAgenda(AgendaRequestDto agendaRequestDto, AuthenticatedUser user) throws CustomException;

    List<AgendaResponseDto> listarAgendas(Timestamp fechaInicial, Timestamp fechaFinal, AuthenticatedUser user) throws CustomException;
}
