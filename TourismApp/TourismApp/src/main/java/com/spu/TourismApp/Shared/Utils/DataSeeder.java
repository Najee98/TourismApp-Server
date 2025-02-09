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

            AppUser firstAgency = new AppUser();
            firstAgency.setFirstName("agency1");
            firstAgency.setLastName("agency1");
            firstAgency.setEmail("agency1@agency1.com");
            firstAgency.setPassword(passwordEncoder.encode("agency1234"));
            firstAgency.setRole(AGENCY_MANAGER);

            AppUser secondAgency = new AppUser();
            secondAgency.setFirstName("agency2");
            secondAgency.setLastName("agency2");
            secondAgency.setEmail("agency2@agency2.com");
            secondAgency.setPassword(passwordEncoder.encode("agency1234"));
            secondAgency.setRole(AGENCY_MANAGER);

            AppUser hotel = new AppUser();
            hotel.setFirstName("hotel");
            hotel.setLastName("hotel");
            hotel.setEmail("hotel@hotel.com");
            hotel.setPassword(passwordEncoder.encode("hotel1234"));
            hotel.setRole(HOTEL_MANAGER);

            AppUser restaurant = new AppUser();
            restaurant.setFirstName("restaurant");
            restaurant.setLastName("restaurant");
            restaurant.setEmail("restaurant@restaurant.com");
            restaurant.setPassword(passwordEncoder.encode("restaurant1234"));
            restaurant.setRole(RESTAURANT_MANAGER);

            userRepository.save(admin);
            userRepository.save(user);
            userRepository.save(firstAgency);
            userRepository.save(secondAgency);
            userRepository.save(hotel);
            userRepository.save(restaurant);
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
                    15,
                    userRepository.findById(1).get()));

            hotelRepository.save(new Hotel(
                    null,
                    "Hotel New York",
                    "The greatest hotel in the east",
                    "Hotelllay street",
                    "099 654321",
                    "testImage2",
                    25,
                    userRepository.findById(2).get()));
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
                    12,
                    userRepository.findById(1).get()));

            restaurantRepository.save(new Restaurant(
                    null,
                    "RestauRastaaaayyyyy",
                    "The greatest restaurant in the east",
                    "Restaurant streetaaaayyyy",
                    "099 654321",
                    "testImage4",
                    33,
                    userRepository.findById(2).get()));
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
                   // userRepository.findById(1).get()
                    ));

            attractionRepository.save(new Attraction(
                    null,
                    "Central Peeeeerk",
                    "The greatest place in F.R.I.E.N.D.S",
                    "Friends street",
                    "088 555444",
                    "testImage6"
                 //   userRepository.findById(2).get()
                    ));
        }

        // Seed Agencies
        if(agencyRepository.count() == 0) {

            AppUser user = userRepository.findById(3).get();

            Agency agency = new Agency(
                    null,
                    "The greatest agency in the west",
                    "West-side yo",
                    "011 123456",
                    "testImage7",
                    user
            );
            agencyRepository.save(agency);


            user.setAgency(agency);

            userRepository.save(user);

            user = userRepository.findById(4).get();

            agency = new Agency(
                    null,
                    "The greatest agency in the east",
                    "East-siiiide",
                    "099 654321",
                    "testImage8",
                    user
            );
            agencyRepository.save(agency);

            user.setAgency(agency);

            userRepository.save(user);
        }
    }
}
