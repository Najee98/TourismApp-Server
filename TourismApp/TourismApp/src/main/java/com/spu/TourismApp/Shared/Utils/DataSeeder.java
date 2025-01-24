package com.spu.TourismApp.Shared.Utils;

import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Attraction;
import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Models.Restaurant;
import com.spu.TourismApp.Models.Utils.Role;
import com.spu.TourismApp.Repositories.AppUserRepository;
import com.spu.TourismApp.Repositories.AttractionRepository;
import com.spu.TourismApp.Repositories.HotelRepository;
import com.spu.TourismApp.Repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.spu.TourismApp.Models.Utils.Role.ADMIN;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final AppUserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final RestaurantRepository restaurantRepository;
    private final AttractionRepository attractionRepository;

//    public DataSeeder(AppUserRepository userRepository,
//                      HotelRepository hotelRepository,
//                      RestaurantRepository restaurantRepository,
//                      AttractionRepository attractionRepository) {
//        this.userRepository = userRepository;
//        this.hotelRepository = hotelRepository;
//        this.restaurantRepository = restaurantRepository;
//        this.attractionRepository = attractionRepository;
//    }

    @Override
    public void run(String... args) throws Exception {
        // Seed Admin User
        if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
            AppUser admin = new AppUser();
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword("admin1234"); // No-op encoding for plain text (use a proper encoder in production)
            admin.setRole(ADMIN);

            userRepository.save(admin);
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
    }
}
