package com.api.services.interfaces;

import com.api.dto.request.AgendaRequestDto;
import com.api.dto.response.AgendaResponseDto;
import com.api.utils.exceptions.CustomException;

public interface IAgendaService {
    void registrarAgenda(AgendaRequestDto agendaRequestDto) throws CustomException;
}
