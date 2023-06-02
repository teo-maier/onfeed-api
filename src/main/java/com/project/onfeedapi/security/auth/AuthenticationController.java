package com.project.onfeedapi.security.auth;

import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.repository.EmployeeRepository;
import com.project.onfeedapi.security.UserDetailsImpl;
import com.project.onfeedapi.security.config.JWTService;
import com.project.onfeedapi.utils.EmployeeType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTService jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthenticationRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // Create new user's account
        Employee user = new Employee(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
//                signUpRequest.getType()
        );

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User userDetails = (User) authentication.getPrincipal();


        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails, roles);

        String[] onlyJwt = jwtCookie.toString().substring(13).split(";");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        responseHeaders.set(HttpHeaders.AUTHORIZATION, onlyJwt[0]);
        return ResponseEntity.ok().headers(responseHeaders)
                .body(userDetails);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> register(
//            @RequestBody RegisterRequest request
//    ) {
//        return ResponseEntity.ok(service.register(request));
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<?> authenticate(
//            @RequestBody AuthenticationRequest request
//    ) {
//        Object employee = service.authenticate(request);
//        return ResponseEntity.ok(employee);
//    }

//    @PostMapping("/refresh-token")
//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        service.refreshToken(request, response);
//    }
}