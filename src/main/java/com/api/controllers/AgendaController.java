package com.api.controllers;

import com.api.config.UsuarioContextHolder;
import com.api.config.security.annotation.PermitirRoles;
import com.api.dto.AuthenticatedUser;
import com.api.dto.request.AgendaRequestDto;
import com.api.dto.response.ApiResponseDto;
import com.api.model.enums.TipoUsuarioEnum;
import com.api.services.interfaces.IAgendaService;
import com.api.utils.exceptions.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/agenda")
@RestController
@Tag(name = "Agenda", description = "Controller encargado de los método para agenda")
public class AgendaController extends BaseController {

    private final IAgendaService iAgendaService;
    private final UsuarioContextHolder usuarioContextHolder;

    @PermitirRoles({TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.ENTIDAD})
    @Operation(summary = "Post crear agenda", description = "Método encargado de crear agenda.")
    @PostMapping(value = "/crear-agenda", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> registrarAgenda(@RequestBody AgendaRequestDto agendaRequestDto) {
        AuthenticatedUser user = usuarioContextHolder.getUsuario();
        this.iAgendaService.registrarAgenda(agendaRequestDto, user);
        return createSuccessResponse("Registro exitoso.");
    }

    @PermitirRoles({TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.ENTIDAD})
    @Operation(summary = "Get listar agendas fecha", description = "Metodo encargado de listar agendas por fecha.")
    @GetMapping(value = "/listar-agenda", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> listarAgendas(@RequestParam Timestamp fechaInicial, @RequestParam Timestamp fechaFinal) throws CustomException {
        AuthenticatedUser user = usuarioContextHolder.getUsuario();
        var response = iAgendaService.listarAgendas(fechaInicial, fechaFinal, user);
        return createSuccessResponse(response);
    }

}
