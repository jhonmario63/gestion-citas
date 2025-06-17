package com.api.utils;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MensajesEnum {
    USUARIO_EXISTENTE("El correo ya está registrado."),
    DOCUMENTO_EXISTENTE("El documento ya está registrado."),
    USUARIO_INCORRECTO("Usuario y contraseña incorrecto."),
    ERROR_LOGIN("Usuario y contraseña incorrecto."),
    USUARIO_NO_EXISTENTE("Usuario no encontrado."),
    USUARIO_ERROR_PERMISO("No tiene permisos para crear usuarios de tipo: "),
    ERROR_REGISTRO_USUARIO("Error al registrar el usuario."),
    ERROR_ACTUALIZAR_USUARIO("Error al actualizar el usuario."),
    CORREO_EXISTENTE("El correo electrónico ya está en uso."),
    ERROR_SERVIDOR("Ocurrió un error: "),
    ERROR_REGISTRO_ENTIDAD("Error al registrar la entidad."),
    ERROR_REGISTRO_AGENDA("Error al registrar la agenda."),
    AGENDA_NO_EXISTENTE("Agenda no encontrada"),
    AGENDA_LIST_NO_EXISTENTE("No se encontraron agendas en el rango de fechas indicado."),
    CITAS_NO_EXISTENTE("No se encontraron citas en el rango de fechas indicado."),
    ENTIDAD_NO_EXISTENTE("Entidad no encontrada"),
    ENTIDAD_EXISTENTE("La entidad ya existente."),
    ACCESO_DENEGADO("Acceso denegado: su rol no permite realizar esta operación."),
    AGENDA_EXISTENTE("Agenda ya existente."),;
    private final String msg;
}
