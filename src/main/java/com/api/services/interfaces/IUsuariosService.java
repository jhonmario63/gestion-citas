package com.api.services.interfaces;

import com.api.dto.AuthenticatedUser;
import com.api.dto.request.LoginRequestDto;
import com.api.dto.request.UsuarioRequestDto;
import com.api.dto.response.UsuarioResponseDto;
import com.api.utils.exceptions.CustomException;

public interface IUsuariosService {
    void registrarUsuario(UsuarioRequestDto usuarioRequestDto, AuthenticatedUser user) throws CustomException;

    void actualizarUsuario(UsuarioRequestDto usuarioRequestDto, AuthenticatedUser user) throws CustomException;

    UsuarioResponseDto login(LoginRequestDto loginRequestDto) throws CustomException;
}
