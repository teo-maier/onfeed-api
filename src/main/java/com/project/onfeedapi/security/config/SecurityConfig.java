package com.project.onfeedapi.security.config;

import com.project.onfeedapi.repository.EmployeeRepository;
import com.project.onfeedapi.security.CustomAuthFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final EmployeeRepository employeeRepository;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/form").permitAll()
                .requestMatchers(HttpMethod.GET, "/form/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/form/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/question").permitAll()
                .requestMatchers(HttpMethod.PUT, "/question/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/question/**").permitAll()
                .anyRequest()
                .authenticated()
//                .logout()
//                .logoutUrl("/api/v1/auth/logout")
//                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthFailureHandler();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        corsConfiguration.addExposedHeader(HttpHeaders.CONTENT_DISPOSITION);
        corsConfiguration.addExposedHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS);
        corsConfiguration.addExposedHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS);
        corsConfiguration.addExposedHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN);
        corsConfiguration.addAllowedOrigin("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
