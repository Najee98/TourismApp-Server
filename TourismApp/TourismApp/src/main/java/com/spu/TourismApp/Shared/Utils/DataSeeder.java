package com.spu.TourismApp.Shared.Utils;

import com.spu.TourismApp.Models.*;
import com.spu.TourismApp.Models.Utils.ReservationType;
import com.spu.TourismApp.Models.Utils.Role;
import com.spu.TourismApp.Repositories.*;
import com.spu.TourismApp.Services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.spu.TourismApp.Models.Utils.Role.*;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final AppUserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final RestaurantRepository restaurantRepository;
    private final AttractionRepository attractionRepository;
    private final AgencyRepository agencyRepository;
    private final ReservationRepository reservationRepository;
    private final TourRepository tourRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Seed Admin User
        if (userRepository.findByEmail("admin@tourism.com").isEmpty()
            && userRepository.findByEmail("user@tourism.com").isEmpty()) {

            AppUser admin = new AppUser();
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setEmail("admin@tourism.com");
            admin.setPassword(passwordEncoder.encode("123456")); // No-op encoding for plain text (use a proper encoder in production)
            admin.setRole(ADMIN);

            AppUser user = new AppUser();
            user.setFirstName("user");
            user.setLastName("user");
            user.setEmail("user@tourism.com");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRole(USER);

            AppUser firstAgency = new AppUser();
            firstAgency.setFirstName("agency1");
            firstAgency.setLastName("agency1");
            firstAgency.setEmail("agency1@tourism.com");
            firstAgency.setPassword(passwordEncoder.encode("123456"));
            firstAgency.setRole(AGENCY_MANAGER);

            AppUser secondAgency = new AppUser();
            secondAgency.setFirstName("agency2");
            secondAgency.setLastName("agency2");
            secondAgency.setEmail("agency2@tourism.com");
            secondAgency.setPassword(passwordEncoder.encode("123456"));
            secondAgency.setRole(AGENCY_MANAGER);

            AppUser hotel = new AppUser();
            hotel.setFirstName("hotel");
            hotel.setLastName("hotel");
            hotel.setEmail("hotel@tourism.com");
            hotel.setPassword(passwordEncoder.encode("123456"));
            hotel.setRole(HOTEL_MANAGER);

            AppUser restaurant = new AppUser();
            restaurant.setFirstName("restaurant");
            restaurant.setLastName("restaurant");
            restaurant.setEmail("restaurant@tourism.com");
            restaurant.setPassword(passwordEncoder.encode("123456"));
            restaurant.setRole(RESTAURANT_MANAGER);

            userRepository.save(admin);
            userRepository.save(user);
            userRepository.save(firstAgency);
            userRepository.save(secondAgency);
            userRepository.save(hotel);
            userRepository.save(restaurant);
        }
    }
}
