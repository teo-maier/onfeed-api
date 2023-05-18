package com.project.onfeedapi.security.config;

import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.repository.EmployeeRepository;
import com.project.onfeedapi.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;


//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userEmail;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        // jwt is not refreshed !!!
//        jwt = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(jwt);
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//            // get token from request header
//            if (jwtService.isTokenValid(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        // add necessary response headers
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//        final User principal = getPrincipal(userDetails.getUsername(), userDetails.getPassword());
//        Employee employee = employeeRepository.findByEmail(principal.getUsername());
//        String authority = !principal.getAuthorities().isEmpty() ? userDetails.getAuthorities().toArray()[0].toString() : null;
//
//        List<String> exposedHeaders = Arrays.asList("Authorization", "id", "user_route", "Role", "firstLogin");
//        String exposedHeadersStringList = exposedHeaders.toString();
//        String exposedHeadersString = exposedHeadersStringList.substring(1, exposedHeadersStringList.length() - 1);
//
//        response.addHeader("Access-Control-Expose-Headers", exposedHeadersString);
//        response.addHeader("Role", authority);
//        response.addHeader("id", String.valueOf(employee.getId()));
//        var jwtToken = jwtService.generateToken(principal);
//        response.addHeader("Authorization", "Bearer " + jwtToken);
//        filterChain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtService.validateJwtToken(jwt)) {
                String username = jwtService.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

//        UserDetails userDetails =
//                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User ud = (User) authentication.getPrincipal();
//
//        List<String> exposedHeaders = Arrays.asList("Set-Cookie", "id", "user_route", "Role", "firstLogin");
//        String exposedHeadersStringList = exposedHeaders.toString();
//        String exposedHeadersString = exposedHeadersStringList.substring(1, exposedHeadersStringList.length() - 1);
//
//        response.addHeader("Access-Control-Expose-Headers", exposedHeadersString);
//        response.addHeader("Set-Cookie", String.format("ILikeCookies=%s", jwt));

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        return jwtService.getJwtFromCookies(request);
    }
}