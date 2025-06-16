package com.api.controllers;

import com.api.config.UsuarioContextHolder;
import com.api.config.security.annotation.PermitirRoles;
import com.api.dto.AuthenticatedUser;
import com.api.dto.request.EntidadRequestDto;
import com.api.dto.response.ApiResponseDto;
import com.api.model.enums.TipoUsuarioEnum;
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
    private final UsuarioContextHolder usuarioContextHolder;

    @PermitirRoles({TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.ENTIDAD})
    @Operation(summary = "Post registrar entidad", description = "Método encargado de registrar la entidad.")
    @PostMapping(value = "registrar-entidad", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> registrarEntidad(@RequestBody EntidadRequestDto entidadRequestDto) throws CustomException {
        AuthenticatedUser user = usuarioContextHolder.getUsuario();
        this.iEntidadesService.registrarEntidad(entidadRequestDto, user);
        return createSuccessResponse("Registro exitoso.");
    }

    @PermitirRoles({TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.ENTIDAD})
    @Operation(summary = "Put actualizar entidad", description = "Método encargado de actualizar la entidad.")
    @PutMapping(value = "actualizar-entidad", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> actualizarEntidad(@RequestBody EntidadRequestDto entidadRequestDto) throws CustomException {
        this.iEntidadesService.actualizarEntidad(entidadRequestDto);
        return createSuccessResponse("Actualización exitosa.");
    }

    @PermitirRoles({TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.ENTIDAD})
    @Operation(summary = "Get listar entidades", description = "Método encargado de listar las entidades.")
    @GetMapping(value = "listar-entidad", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> listarEntidad() throws CustomException {
        var response = this.iEntidadesService.listarEntidad();
        return createSuccessResponse(response);
    }


}
