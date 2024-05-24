package com.org.project.config;

import java.util.List;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.config.Customizer;


@Configuration
@EnableWebSecurity
public class WebConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
    private String allowedOriginVar = "http://localhost:8010";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable).
            cors(Customizer.withDefaults()).authorizeHttpRequests(auth-> auth.requestMatchers(
            "/api/**",
            "/docs/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/docs/swagger-ui.html",
            "/docs/**",
            "/")
          .permitAll().anyRequest().authenticated())
          .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
          .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }

    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry registry) {
    //             logger.info("[LOGGER] Configuring CORS with allowed Origins: ");

    //             registry.addMapping("/**")
    //                     .allowedOrigins("http://localhost:8040")
    //                     .allowedMethods("GET", "POST", "PUT", "DELETE")
    //                     .allowedHeaders("*")
    //                     .allowCredentials(true)
    //                     .maxAge(3600);
    //         }
    //     };
    // }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        logger.info("[LOGGER] Configuring CORSSource with allowed Origins: " + allowedOriginVar);
        
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
            List.of(
                "https://developerportal.thomsonreuters.com",
                "https://contentconsole-qa.int.thomsonreuters.com",
                "http://localhost:8030"));
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "OPTIONS", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setMaxAge(3600L);
        configuration.setExposedHeaders(List.of("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));

        logger.info("[LOGGER] Configuring CORSSource with allowed Origins: " + configuration);


        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
