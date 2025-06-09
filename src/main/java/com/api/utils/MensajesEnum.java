package com.api.utils;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MensajesEnum {
    USUARIO_EXISTENTE("El correo ya está registrado."),
    USUARIO_NO_EXISTENTE("Usuario no encontrado."),
    ERROR_REGISTRO_USUARIO("Error al registrar el usuario."),
    ERROR_ACTUALIZAR_USUARIO("Error al actualizar el usuario."),
    CORREO_EXISTENTE("El correo electrónico ya está en uso.");
    private final String mensaje;
}
