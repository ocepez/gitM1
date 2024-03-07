package com.projetsi.lieux.comm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Classe de configuration pour le partage des ressources inter-origines (CORS).
 *
 * @author Maxime A.
 */
@Configuration
public class CorsConfig {
    /**
     * Crée et configure un bean CorsFilter.
     *
     * @return Bean CorsFilter configuré pour autoriser les requêtes depuis n'importe quelle origine, méthode et en-tête.
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // Autoriser toutes les origines, méthodes et en-têtes.
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
