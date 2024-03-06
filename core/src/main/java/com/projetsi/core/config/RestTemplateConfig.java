package com.projetsi.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration de RestTemplate pour la gestion des appels HTTP dans l'application.
 *
 * @author Maxime A.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Crée et configure un bean RestTemplate.
     *
     * @return Une instance de RestTemplate configurée.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
