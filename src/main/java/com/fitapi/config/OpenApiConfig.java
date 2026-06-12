package com.fitapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "World Cup API",
                version = "1.0",
                description = "API REST para gestao de selecoes, jogadores e partidas da Copa do Mundo"
        )
)
public class OpenApiConfig {
}
