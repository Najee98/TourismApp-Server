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
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('auth:register')")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/user-id")
    @PreAuthorize("hasAnyAuthority('auth:getLoggedInUser')")
    public ResponseEntity<Object> getUserIdFromLogin(){
        return new ResponseEntity<>(userService.getUserFromLogin(), HttpStatus.ACCEPTED);
    }
}