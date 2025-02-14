package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.AuthenticationService;
import com.spu.TourismApp.Services.UserService;
import com.spu.TourismApp.Shared.Dto.Authentication.AuthenticationRequest;
import com.spu.TourismApp.Shared.Dto.Authentication.AuthenticationResponse;
import com.spu.TourismApp.Shared.Dto.Authentication.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor // Generates a constructor with required fields (final fields).
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    /**
     * Registers a new user.
     *
     * @param request The registration request containing user details.
     * @return ResponseEntity containing the authentication response with JWT token.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * Authenticates a user and returns a JWT token upon successful login.
     *
     * @param request The authentication request containing login credentials.
     * @return ResponseEntity containing the authentication response with JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    /**
     * Retrieves the currently authenticated user's ID.
     *
     * @return ResponseEntity containing the user ID.
     */
    @GetMapping("/user-id")
    public ResponseEntity<Object> getUserIdFromLogin() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserFromLogin());
    }
}
