package com.api.controllers;

import com.api.dto.request.EntidadRequestDto;
import com.api.dto.response.ApiResponseDto;
import com.api.services.interfaces.IEntidadesService;
import com.api.utils.exceptions.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/entidades")
@Tag(name = "Entidades", description = "Controller encargado de los método para entidades")
public class EntidadesController extends BaseController {

    private final IEntidadesService iEntidadesService;

    @Operation(summary = "Post registrar entidad", description = "Método encargado de registrar la entidad.")
    @PostMapping(value = "registrar-entidad", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> registrarEntidad(@RequestBody EntidadRequestDto entidadRequestDto) throws CustomException {
        this.iEntidadesService.registrarEntidad(entidadRequestDto);
        return createSuccessResponse("Registro exitoso.");
    }

    @Operation(summary = "Put actualizar entidad", description = "Método encargado de actualizar la entidad.")
    @PutMapping(value = "actualizar-entidad", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> actualizarEntidad(@RequestBody EntidadRequestDto entidadRequestDto) throws CustomException {
        this.iEntidadesService.actualizarEntidad(entidadRequestDto);
        return createSuccessResponse("Actualización exitosa.");
    }

}
