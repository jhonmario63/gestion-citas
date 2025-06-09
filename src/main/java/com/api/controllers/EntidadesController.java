package com.api.controllers;

import com.api.dto.request.EntidadRequestDto;
import com.api.dto.response.ApiResponseDto;
import com.api.services.interfaces.IEntidadesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/entidades")
public class EntidadesController extends BaseController {

    private final IEntidadesService iEntidadesService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> registrarEntidad(@RequestBody EntidadRequestDto entidadRequestDto) {
        var response = iEntidadesService.registrarEntidad(entidadRequestDto);
        return createSuccessResponse(response);
    }

}
