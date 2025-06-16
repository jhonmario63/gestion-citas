package com.api.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoUsuarioEnum {
    ADMIN,
    ENTIDAD,
    USER,
    FUNCIONARIO
}
