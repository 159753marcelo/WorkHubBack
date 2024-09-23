package com.marcelo.workhub.Infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("WorkHub API")
                        .description("WorkHub é uma API  integrará os recém-formados autônomos aos clientes municipais, promovendo espaços de trabalho, intermediação de serviços e suporte educacional. Nós, da Work Hub, proporcionaremos mais rapidez para serviços cotidianos e essenciais para nossos clientes")
                        .contact(new Contact()
                                .name("Marcelo Costa, Abide Klaus, Ronaldo Moura, Alysson Nunes, Pedro Lucas")
                                .url("https://github.com/159753marcelo/WorkHubBack")));

    }


}
