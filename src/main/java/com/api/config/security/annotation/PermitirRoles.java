package com.api.config.security.annotation;

import com.api.model.enums.TipoUsuarioEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermitirRoles {
    TipoUsuarioEnum[] value();
}
