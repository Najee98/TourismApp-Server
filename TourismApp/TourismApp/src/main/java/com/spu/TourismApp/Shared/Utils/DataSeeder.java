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

import static com.spu.TourismApp.Models.Utils.Role.ADMIN;
import static com.spu.TourismApp.Models.Utils.Role.USER;

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
        if (userRepository.findByEmail("admin@admin.com").isEmpty()
            && userRepository.findByEmail("user@user.com").isEmpty()) {
            AppUser admin = new AppUser();
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword(passwordEncoder.encode("admin1234")); // No-op encoding for plain text (use a proper encoder in production)
            admin.setRole(ADMIN);

            AppUser user = new AppUser();
            user.setFirstName("user");
            user.setLastName("user");
            user.setEmail("user@user.com");
            user.setPassword(passwordEncoder.encode("user1234"));
            user.setRole(USER);

            userRepository.save(admin);
            userRepository.save(user);
        }

        // Seed Hotels
        if (hotelRepository.count() == 0) {
            hotelRepository.save(new Hotel(
                    null,
                    "Hotel California",
                    "The greatest hotel in the west",
                    "Hotel street",
                    "011 123456",
                    "testImage1",
                    15));

            hotelRepository.save(new Hotel(
                    null,
                    "Hotel New York",
                    "The greatest hotel in the east",
                    "Hotelllay street",
                    "099 654321",
                    "testImage2",
                    25));
        }

        // Seed Restaurants
        if (restaurantRepository.count() == 0) {
            restaurantRepository.save(new Restaurant(
                    null,
                    "RestauRast",
                    "The greatest restaurant in the west",
                    "Restaurant street",
                    "011 12356",
                    "testImage3",
                    12));

            restaurantRepository.save(new Restaurant(
                    null,
                    "RestauRastaaaayyyyy",
                    "The greatest restaurant in the east",
                    "Restaurant streetaaaayyyy",
                    "099 654321",
                    "testImage4",
                    33));
        }

        // Seed Attractions
        if (attractionRepository.count() == 0) {
            attractionRepository.save(new Attraction(
                    null,
                    "Central Park",
                    "The greatest attraction in the east",
                    "Santa Claws street",
                    "099 654321",
                    "testImage5"
                    ));

            attractionRepository.save(new Attraction(
                    null,
                    "Central Peeeeerk",
                    "The greatest place in F.R.I.E.N.D.S",
                    "Friends street",
                    "088 555444",
                    "testImage6"
                    ));
        }

        // Seed Agencies
        if(agencyRepository.count() == 0) {
            agencyRepository.save(new Agency(
                    null,
                    "The greatest agency in the west",
                    "West-side yo",
                    "011 123456",
                    "testImage7",
                    userRepository.findById(1).orElse(null)
                    ));
            agencyRepository.save(new Agency(
                    null,
                    "The greatest agency in the east",
                    "East-siiiide",
                    "099 654321",
                    "testImage8",
                    userRepository.findById(2).orElse(null)
            ));
        }
    }
}
