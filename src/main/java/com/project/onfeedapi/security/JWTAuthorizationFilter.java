//package com.project.onfeedapi.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Objects;
//
//import static com.project.onfeedapi.security.utils.SecurityConstants.*;
//
//public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
//
//    public JWTAuthorizationFilter(final AuthenticationManager authManager) {
//        super(authManager);
//    }
//
//    @Override
//    protected void doFilterInternal(final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain) throws IOException, ServletException {
//        final String header = req.getHeader(AUTHORIZATION);
//
//        if (Objects.isNull(header) || !header.startsWith(TOKEN_PREFIX)) {
//            chain.doFilter(req, res);
//            return;
//        }
//
//        final UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        chain.doFilter(req, res);
//    }
//
//    public static UsernamePasswordAuthenticationToken getAuthentication(final String token) {
//        if (token != null) {
//            // parse the token.
//            Jws<Claims> claimsJws = Jwts.parser()
//                    .setSigningKey(SECRET.getBytes())
//                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
//            final String user = claimsJws.getBody().getSubject();
//            final String role = claimsJws.getBody().get(AUTHORITIES_KEY, String.class);
//
//            if (user != null && role != null) {
//                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority(role));
//                return new UsernamePasswordAuthenticationToken(user, null, authorities);
//            }
//            return null;
//        }
//        return null;
//    }
//}
