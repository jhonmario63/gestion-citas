package com.api.controllers;

import com.api.config.security.annotation.PermitirRoles;
import com.api.dto.request.CitasRequestDto;
import com.api.dto.response.ApiResponseDto;
import com.api.model.enums.TipoUsuarioEnum;
import com.api.services.interfaces.ICitasService;
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
@RequestMapping("/cita")
@RestController
@Tag(name = "Citas", description = "Controller encargado de los método para citas")
public class CitasController extends BaseController {

    private final ICitasService iCitasService;

    @Operation(summary = "Get listar cita por fecha", description = "Metodo encargado de listar cita por fecha.")
    @GetMapping(value = "/listar-citas-fecha", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> listarCita(@RequestParam Timestamp fechaInicial, @RequestParam Timestamp fechaFinal) throws CustomException {
        var response = iCitasService.listarCitas(fechaInicial, fechaFinal);
        return createSuccessResponse(response);
    }

    @PermitirRoles({TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.ENTIDAD})
    @Operation(summary = "Post registrar cita", description = "Método encargado de registrar cita.")
    @PostMapping(value = "/registra-cita", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> registrarCita(@RequestBody CitasRequestDto citasRequestDto) throws CustomException {
        this.iCitasService.registrarCita(citasRequestDto);
        return createSuccessResponse("Registro exitoso.");
    }

}
