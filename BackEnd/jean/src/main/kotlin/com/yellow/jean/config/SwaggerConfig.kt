package com.yellow.jean.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .addSecurityItem(SecurityRequirement().addList(SECURITY_SCHEME))
        .components(
            Components().addSecuritySchemes(
                SECURITY_SCHEME, SecurityScheme()
                    .name(SECURITY_SCHEME)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            )
        )
        .info(apiInfo())

    private fun apiInfo() = Info()
        .title("API Docs")
        .description("Board API Docs 입니다.")
        .termsOfService("http://swagger.io/terms/")
        .contact(Contact()
            .name("LeeSeongMoo")
            .url("https://github.com/SM-Lee95")
            .email("sm-lee95@nate.com"))
        .license(License()
            .name("Apache License Version 2.0")
            .url("https://www.apache.org/licenses/LICENSE-2.0"));

    companion object {
        private const val SECURITY_SCHEME: String = "auth"
    }
}