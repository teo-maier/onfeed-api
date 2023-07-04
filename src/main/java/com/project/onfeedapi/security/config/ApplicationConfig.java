package com.project.onfeedapi.security.config;

import com.project.onfeedapi.repository.EmployeeRepository;
import com.project.onfeedapi.security.CustomAuthFailureHandler;
import com.project.onfeedapi.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@EnableWebSecurity
@Configuration
public class ApplicationConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public JWTAuthenticationFilter authenticationJwtTokenFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/signin").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/signout").permitAll()
                .requestMatchers(HttpMethod.POST, "/form").permitAll()
                .requestMatchers(HttpMethod.GET, "/form/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/form/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/question").permitAll()
                .requestMatchers(HttpMethod.PUT, "/question/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/question/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/team/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/session").permitAll()
                .requestMatchers(HttpMethod.PUT, "/session").permitAll()
                .requestMatchers(HttpMethod.GET, "/session/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/session-recipients").permitAll()
                .requestMatchers(HttpMethod.GET, "/session-recipients/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/employee/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/employee").permitAll()
                .requestMatchers(HttpMethod.GET, "/answer/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/answer").permitAll()
                .requestMatchers(HttpMethod.PUT, "/answer").permitAll()
                .anyRequest()
                .authenticated()
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
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        corsConfiguration.addExposedHeader(HttpHeaders.CONTENT_DISPOSITION);
        corsConfiguration.addExposedHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS);
        corsConfiguration.addExposedHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS);
        corsConfiguration.addExposedHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN);
        corsConfiguration.addExposedHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS);
        corsConfiguration.addExposedHeader(HttpHeaders.AUTHORIZATION);
        corsConfiguration.addAllowedOriginPattern("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}