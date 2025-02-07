package org.example.kotlindemo.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class OpenApiConfig {

    @Profile("!auth")
    @Bean
    fun openApi(@Value("\${springdoc.version}") version: String): OpenAPI {
        val serverList = getServerList()
        return OpenAPI()
            .info(getApiInfo(version))
            .servers(serverList)
    }

    @Profile("auth")
    @Bean
    fun openApiWithAuthentication(@Value("\${springdoc.version}") version: String): OpenAPI {
        val serverList = getServerList()
        val bearerAuth = SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .`in`(SecurityScheme.In.HEADER)
            .name("bearerAuth")
        val securityRequirementList = listOf(SecurityRequirement().addList("bearerAuth"))
        return OpenAPI()
            .components(Components().addSecuritySchemes("bearerAuth", bearerAuth))
            .security(securityRequirementList)
            .info(getApiInfo(version))
            .servers(serverList)
    }

    private fun getApiInfo(version: String): Info {
        return Info()
            .title("API 문서")
            .version(version)
            .description("잘못된 부분이나 오류 발생 시 말씀해주세요.")
    }

    private fun getServerList(): List<Server> {
        return listOf(
            generateServer("http://localhost:8080", "Localhost SERVER URL"),
            generateServer("https://dev.demo.com", "Develop SERVER URL"),
            generateServer("https://qa.demo.com", "QA SERVER URL"),
            generateServer("https://prd.demo.com", "Production SERVER URL")
        )
    }

    private fun generateServer(url: String, description: String): Server {
        return Server().apply {
            this.url = url
            this.description = description
        }
    }
}