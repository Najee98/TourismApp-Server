package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Repositories.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppUserRepository userRepository;

    @Transactional
    @Override
    public AppUser getUserFromLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        AppUser user = (AppUser) authentication.getPrincipal();

        return user;
    }

    @Override
    public AppUser getUserById(Integer userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found"));

        return user;
    }

    @Override
    public void saveUser(AppUser user) {
        userRepository.save(user);
    }
}