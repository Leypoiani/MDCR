package projeto.dados.credito.rural.mdcr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
    @Configuration
    @EnableSwagger2
    public class SwaggerConfig {
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("projeto.dados.credito.rural.mdcr"))
                    .paths(PathSelectors.ant("/api/v1/**"))
                    .build()
                    .apiInfo(apiInfo());
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .contact(new Contact("Leianny Poiani", "https://github.ibm.com/Leianny-Poiani", "leianny.poiani@ibm.com"))
                    .title("Projeto")
                    .description("Desafio Matriz de Dados do Crédito Rural - MDCR IBM - Utilizando boas práticas de desenvolvimento no software, crie uma aplicação para ler, salvar e apresentar dados da lista da Matriz de Dados do Crédito Rural - MDCR do Banco Central do Brasil sobre Contratos de Comercialização por Produto, Região e UF.")
                    .license("Apache Licence Version 2.0")
                    .licenseUrl("https://apache.org")
                    .version("1.0")
                    .build();

        }
    }

