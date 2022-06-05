package com.br.edercnj.credentials.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.temporal.Temporal;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerSpringConfig {
    @Bean
    @Profile("!local")
    public UiConfiguration uiConfiguration() {
        final String[] methodsWithTryOutButton = {""};
        return UiConfigurationBuilder.builder().supportedSubmitMethods(methodsWithTryOutButton).build();
    }

    @Bean
    public Docket apiDocumentationPackage() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.br.edercnj.credentials.application.web.api.v1"))
                .build()
                .apiInfo(API_INFO).directModelSubstitute(Temporal.class, String.class);
    }

    private static final ApiInfo API_INFO = new ApiInfo(
            "Credentials",
            "Api for save Crendetials and validate login for credentials",
            "1.0",
            "Terms of Serice",
            new Contact("Eder", "https://github.com/edercnj", "edercnj@gmail.com"), "GNU General Public License 3",
            "https://www.gnu.org/licenses/why-not-lgpl.html", Collections.emptyList());

}
