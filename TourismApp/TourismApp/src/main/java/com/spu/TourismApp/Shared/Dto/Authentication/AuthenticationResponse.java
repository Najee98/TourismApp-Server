package com.spu.TourismApp.Shared.Dto.Authentication;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String token;
    private boolean isAuthenticated;

}