package com.api.controllers;

import com.api.config.UsuarioContextHolder;
import com.api.config.security.annotation.PermitirRoles;
import com.api.dto.AuthenticatedUser;
import com.api.dto.request.LoginRequestDto;
import com.api.dto.request.UsuarioRequestDto;
import com.api.dto.response.ApiResponseDto;
import com.api.model.enums.TipoUsuarioEnum;
import com.api.services.interfaces.IUsuariosService;
import com.api.utils.exceptions.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Controller encargado de los método para usuarios")
public class UsuariosController extends BaseController {

    private final IUsuariosService iUsuariosService;
    private final UsuarioContextHolder usuarioContextHolder;

    @Operation(summary = "Post login", description = "Método encargado de iniciar sesion.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws CustomException {
        var response = this.iUsuariosService.login(loginRequestDto);
        return createSuccessResponse(response);
    }


    @PermitirRoles({TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.ENTIDAD, TipoUsuarioEnum.FUNCIONARIO})
    @Operation(summary = "Post registrar usuario", description = "Método encargado de crear usuario.")
    @PostMapping(value = "registrar-usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> registrarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto) throws CustomException {
        AuthenticatedUser user = usuarioContextHolder.getUsuario();
        this.iUsuariosService.registrarUsuario(usuarioRequestDto, user);
        return createSuccessResponse("Registro exitoso.");
    }

    @PermitirRoles({TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.ENTIDAD, TipoUsuarioEnum.USER, TipoUsuarioEnum.FUNCIONARIO})
    @Operation(summary = "Put actualizar usuario", description = "Método encargado de actualizar usuario.")
    @PutMapping(value = "actualizar-usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> actualizarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto) throws CustomException {
        AuthenticatedUser user = usuarioContextHolder.getUsuario();
        this.iUsuariosService.actualizarUsuario(usuarioRequestDto, user);
        return createSuccessResponse("Actualización exitosa.");
    }


}
