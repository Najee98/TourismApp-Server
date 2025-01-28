package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Shared.Dto.Agency.AgenciesUsersDto;

import java.util.List;

public interface UserService {

    AppUser getUserFromLogin();

    AppUser getUserById(Integer userId);

    List<AgenciesUsersDto> getAllUsersForAgencies();

    void saveUser(AppUser user);
}