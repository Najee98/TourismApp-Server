package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.DuplicatedResourceException;
import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Utils.Role;
import com.spu.TourismApp.Repositories.AppUserRepository;
import com.spu.TourismApp.Security.JWT.JwtService;
import com.spu.TourismApp.Shared.Dto.Authentication.AuthenticationRequest;
import com.spu.TourismApp.Shared.Dto.Authentication.AuthenticationResponse;
import com.spu.TourismApp.Shared.Dto.Authentication.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Optional<AppUser> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new DuplicatedResourceException("Username already used.");
        } else {
            AppUser user = AppUser.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .imageUrl(null)
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.valueOf(request.getRole()))
                    .build();

            userRepository.save(user);

            var jwtToken = "Bearer " + jwtService.generateToken(user);

            AuthenticationResponse response = new AuthenticationResponse(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getRole().toString(),
                    jwtToken,
                    true
            );

            return response;
        }
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found during authentication."));

        var jwtToken = "Bearer " + jwtService.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().toString(),
                jwtToken,
                true
        );

        return response;
    }
}