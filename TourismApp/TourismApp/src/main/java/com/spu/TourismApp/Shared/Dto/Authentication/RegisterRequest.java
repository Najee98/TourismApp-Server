package com.spu.TourismApp.Shared.Dto.Authentication;

import com.spu.TourismApp.Models.Utils.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

}
