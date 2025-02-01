package com.spu.TourismApp.Services.Utils;

import com.spu.TourismApp.Models.Agency;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilsService {

    private final UserService userService;


    public Agency getLoggedInUserAgency() {
        AppUser agencyManager = userService.getUserFromLogin();
        return agencyManager.getAgency();
    }
}
