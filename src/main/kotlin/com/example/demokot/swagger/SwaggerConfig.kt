package com.example.demokot.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig (
    @Value("\${swg.server.url}")
    private val serverUrl: String,
    @Value("\${swg.server.description}")
    private val serverDescription: String
){
    @Bean
    fun customOpenAPI(): OpenAPI =
        OpenAPI()
            .info(getInfo())
            .addServersItem(Server().url(serverUrl).description(serverDescription))
            .components(createComponents())

    private fun getInfo(): Info =
        Info()
            .title("Demo KT")
            .description(
                "Application for using spring boot with kotlin."
            )
            .version("1.0")
            .license(
                License()
                    .name("Alness Zadro")
                    .url("https://github.com/Alness1314")
            )

    private fun createComponents(): Components =
        Components()

}