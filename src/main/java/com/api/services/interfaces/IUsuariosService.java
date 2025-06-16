package com.api.services.interfaces;

import com.api.dto.request.LoginRequestDto;
import com.api.dto.request.UsuarioRequestDto;
import com.api.dto.response.UsuarioResponseDto;
import com.api.utils.exceptions.CustomException;

import java.util.List;

public interface IUsuariosService {
    void registrarUsuario(UsuarioRequestDto usuarioRequestDto) throws CustomException;

    void actualizarUsuario(UsuarioRequestDto usuarioRequestDto) throws CustomException;

    UsuarioResponseDto login(LoginRequestDto loginRequestDto) throws CustomException;
}
