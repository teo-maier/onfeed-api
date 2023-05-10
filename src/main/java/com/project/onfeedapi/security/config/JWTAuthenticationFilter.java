package com.project.onfeedapi.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.repository.EmployeeRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final EmployeeRepository employeeRepository;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // jwt is not refreshed !!!
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // get token from request header
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // add necessary response headers
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        String authority = !userDetails.getAuthorities().isEmpty() ? userDetails.getAuthorities().toArray()[0].toString() : null;
        List<String> exposedHeaders = Arrays.asList("Authorization", "id", "user_route", "Role", "firstLogin");
        String exposedHeadersStringList = exposedHeaders.toString();
        String exposedHeadersString = exposedHeadersStringList.substring(1, exposedHeadersStringList.length() - 1);
        response.addHeader("Access-Control-Expose-Headers", exposedHeadersString);
        response.addHeader("Role", authority);

        Employee employee = employeeRepository.findByEmail(userEmail);
        response.addHeader("id", String.valueOf(employee.getId()));
        var jwtToken = jwtService.generateToken(employee);
        response.addHeader("Authorization", "Bearer " + jwtToken);
        filterChain.doFilter(request, response);
    }
}