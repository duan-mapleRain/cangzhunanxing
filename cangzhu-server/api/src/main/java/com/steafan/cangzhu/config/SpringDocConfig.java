package com.steafan.cangzhu.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author AnselYuki
 */
@SpringBootConfiguration
public class SpringDocConfig {

    @Bean
    public OpenAPI emergencyLogistics() {
        return new OpenAPI()
                .info(docInfos())
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub repo")
                        .url("https://github.com/MaaAssistantArknights/MaaBackendCenter"))
                .components(new Components()
                        .addSecuritySchemes("securitySchemeName",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name("securitySchemeHeader")
                                        .description("JWT Authorization header using the Bearer scheme. Example: \"%s: Bearer {token}\"".formatted("securitySchemeHeader"))
                        ));
    }


    private Info docInfos() {
        return new Info()
                .title("title")
                .description("description")
                .version("version")
                .license(new License()
                        .name("GNU Affero General Public License v3.0")
                        .url("https://www.gnu.org/licenses/agpl-3.0.html"));
    }
}
