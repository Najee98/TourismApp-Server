package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Shared.Dto.Agency.ManagementUserDto;

import java.util.List;

public interface UserService {

    AppUser getUserFromLogin();

    AppUser getUserById(Integer userId);

    List<ManagementUserDto> getAllUsersForAgencies();

    List<ManagementUserDto> getAllUsersForHotels();

    List<ManagementUserDto> getAllUsersForRestaurants();

    AppUser getUserWithTours(Integer userId);

    void saveUser(AppUser user);
}