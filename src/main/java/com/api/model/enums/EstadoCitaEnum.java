package com.api.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EstadoCitaEnum {
    DISPONIBLE,
    AGENDADA,
    CANCELADA,
    FINALIZADA
}
