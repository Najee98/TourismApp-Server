package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Repositories.AppUserRepository;
import com.spu.TourismApp.Shared.Dto.Agency.ManagementUserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppUserRepository userRepository;

    @Override
    public AppUser getUserFromLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResourceNotFoundException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof AppUser) {
            return (AppUser) principal;
        } else {
            throw new ResourceNotFoundException("User not found or invalid principal type");
        }
    }

    @Override
    public AppUser getUserById(Integer userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found"));

        return user;
    }

    @Override
    public List<ManagementUserDto> getAllUsersForAgencies() {
        List<AppUser> users = userRepository.getAllUsersForAgencies();

        List<ManagementUserDto> response = new ArrayList<>();

        for (AppUser user : users) {
            ManagementUserDto dto = new ManagementUserDto();

            dto.setId(user.getId());
            dto.setName(user.getFirstName() + " " + user.getLastName());

            response.add(dto);
        }

        return response;
    }

    @Override
    public List<ManagementUserDto> getAllUsersForHotels() {
        List<AppUser> users = userRepository.getAllUsersForHotels();

        List<ManagementUserDto> response = new ArrayList<>();

        for (AppUser user : users) {
            ManagementUserDto dto = new ManagementUserDto();

            dto.setId(user.getId());
            dto.setName(user.getFirstName() + " " + user.getLastName());

            response.add(dto);
        }

        return response;
    }

    @Override
    public List<ManagementUserDto> getAllUsersForRestaurants() {
        List<AppUser> users = userRepository.getAllUsersForRestaurants();

        List<ManagementUserDto> response = new ArrayList<>();

        for (AppUser user : users) {
            ManagementUserDto dto = new ManagementUserDto();

            dto.setId(user.getId());
            dto.setName(user.getFirstName() + " " + user.getLastName());

            response.add(dto);
        }

        return response;
    }

    @Override
    public AppUser getUserWithTours(Integer userId) {
        return userRepository.findByIdWithTours(userId).get();
    }

    @Override
    public void saveUser(AppUser user) {
        userRepository.save(user);
    }




}