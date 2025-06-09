package com.api.controllers;

import com.api.dto.request.UsuarioRequestDto;
import com.api.dto.response.ApiResponseDto;
import com.api.services.interfaces.IUsuariosService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosController extends BaseController {

    private IUsuariosService iUsuariosService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> registrarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        var response = iUsuariosService.registrarUsuario(usuarioRequestDto);
        return createSuccessResponse(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> actualizarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        var response = iUsuariosService.actualizarUsuario(usuarioRequestDto);
        return createSuccessResponse(response);
    }

}
