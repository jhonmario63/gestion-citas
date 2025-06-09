package com.api.services.interfaces;

import com.api.dto.request.UsuarioRequestDto;
import com.api.dto.response.UsuarioResponseDto;
import com.api.utils.exceptions.CustomException;

public interface IUsuariosService {
    UsuarioResponseDto registrarUsuario(UsuarioRequestDto usuarioRequestDto) throws CustomException;

    UsuarioResponseDto actualizarUsuario(UsuarioRequestDto usuarioRequestDto) throws CustomException;
}
