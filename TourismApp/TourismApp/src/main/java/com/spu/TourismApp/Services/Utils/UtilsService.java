package com.spu.TourismApp.Services.Utils;

import com.spu.TourismApp.Models.*;
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

    public Hotel getLoggedInUserHotel() {
        AppUser hotelManager = userService.getUserFromLogin();
        return hotelManager.getHotel();
    }

    public Restaurant getLoggedInUserRestaurant() {
        AppUser agencyManager = userService.getUserFromLogin();
        return agencyManager.getRestaurant();
    }

    public Attraction getLoggedInUserAttraction() {
        AppUser agencyManager = userService.getUserFromLogin();
        return agencyManager.getAttraction();
    }

}
