package com.api.controllers;

import com.api.dto.request.AgendaRequestDto;
import com.api.dto.response.ApiResponseDto;
import com.api.services.interfaces.IAgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/agenda")
@RestController
@Tag(name = "Agenda", description = "Controller encargado de los método para agenda")
public class AgendaController extends BaseController {

    private final IAgendaService iAgendaService;

    @Operation(summary = "Post crear agenda", description = "Método encargado de crear agenda.")
    @PostMapping(value = "/crear-agenda", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> registrarAgenda(@RequestBody AgendaRequestDto agendaRequestDto) {
        this.iAgendaService.registrarAgenda(agendaRequestDto);
        return createSuccessResponse("Registro exitoso.");
    }

}
