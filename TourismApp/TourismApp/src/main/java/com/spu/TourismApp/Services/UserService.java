package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.AppUser;

public interface UserService {

    AppUser getUserFromLogin();

    AppUser getUserById(Integer userId);

    void saveUser(AppUser user);
}