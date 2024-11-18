package com.sepa.back_end.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    /**
     * Configura las reglas de CORS (Cross-Origin Resource Sharing) para la aplicacion
     * 
     * Permite solicitudes de origen "http://localhost:4200" y habilita los metodos
     * HTTP especificados, tales como GET, POST, PUT, DELETE y OPTIONS en todos
     * los endpoints de la aplicacion (indicados por "/**")
     *
     * @param registry el registro CORS utilizado para aplicar las configuraciones
     */
    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**") 
                .allowedOrigins("http://localhost:4200") 
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); 
    }
}
