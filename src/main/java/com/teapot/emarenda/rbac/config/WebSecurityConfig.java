package com.teapot.emarenda.rbac.config;

import com.teapot.emarenda.rbac.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Resource(name = "userService")
    private final UserServiceImpl userDetailsService;

    private final UnauthorizedEntryPoint unauthorizedEntryPoint;

    private final PasswordEncoderConfiguration encoder;

    public WebSecurityConfig(
        UnauthorizedEntryPoint unauthorizedEntryPoint,
        UserServiceImpl userDetailsService,
        PasswordEncoderConfiguration encoder) {
        this.unauthorizedEntryPoint = unauthorizedEntryPoint;
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder.bcryptPasswordEncoder());
        return auth.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:52748",    // Flutter web app (localhost)
            "http://127.0.0.1:52748",    // Flutter web app (IP)
            "http://192.168.1.88:52748", // Flutter web app (local network)
            "http://192.168.1.88:8081",  // Backend server
            "http://localhost:8081"      // Backend server (localhost)
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type",
            "Accept",
            "Accept-Encoding",
            "Accept-Language",
            "Connection",
            "Content-Length",
            "Host",
            "Origin",
            "Referer",
            "User-Agent",
            "X-Requested-With",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
        ));
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type",
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials",
            "X-Content-Type-Options",
            "X-XSS-Protection",
            "Cache-Control",
            "Pragma",
            "Expires",
            "X-Frame-Options"
        ));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                authorizeRequests ->
                    authorizeRequests
                        .requestMatchers(
                            "/users/authenticate",
                            "/users/register",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/api-docs/**",
                            "/v3/api-docs/**",
                            "/swagger-resources/**",
                            "/webjars/**"
                        )
                        .permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow all OPTIONS requests
                        .anyRequest()
                        .authenticated())
            .exceptionHandling(
                exceptionHandling -> exceptionHandling
                    .authenticationEntryPoint(unauthorizedEntryPoint)
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        logger.error("Access Denied for URL: {}", request.getRequestURL());
                        logger.error("Method: {}", request.getMethod());
                        logger.error("Headers: {}", Collections.list(request.getHeaderNames()));
                        logger.error("Error: {}", accessDeniedException.getMessage());
                        
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"Access Denied: " + accessDeniedException.getMessage() + "\"}");
                    }))
            .sessionManagement(
                sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(
            authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationFilter();
    }
}
