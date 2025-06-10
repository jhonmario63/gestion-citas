package com.api.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestión de Citas",
                description = "Documentación para el sistema de agendas y citas",
                version = "1.0.0"
        )
)
public class OpenApiConfig {

}
