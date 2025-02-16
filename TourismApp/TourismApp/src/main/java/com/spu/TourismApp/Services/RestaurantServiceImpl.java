package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Restaurant;
import com.spu.TourismApp.Models.Utils.ReservationType;
import com.spu.TourismApp.Repositories.ReservationRepository;
import com.spu.TourismApp.Repositories.RestaurantRepository;
import com.spu.TourismApp.Services.Utils.UtilsService;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelReservationDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.CreateRestaurantDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final ReservationRepository reservationRepository;
    private final UtilsService utilsService;
    private final UserService userService;


    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        List<RestaurantDto> response = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantDto dto = new RestaurantDto();

            dto = mapRestaurantToDto(restaurant);

            response.add(dto);
        }

        return response;
    }

    @Override
    public RestaurantDto getRestaurantDetails(Integer id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        RestaurantDto response = new RestaurantDto();

        response = mapRestaurantToDto(restaurant);

        return response;
    }

    @Override
    public Restaurant getRestaurant(Integer id){
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with id: " + id + " not found"));
    }

    @Override
    public List<ReservationDetailsDto> getRestaurantReservations() {

        Integer restaurantId = utilsService.getLoggedInUserRestaurant().getId();

        List<RestaurantReservationDto> restaurantReservationsList = reservationRepository.getRestaurantReservations(restaurantId);

        List<ReservationDetailsDto> response = new ArrayList<>();

        for (RestaurantReservationDto reservation : restaurantReservationsList) {
            ReservationDetailsDto dto = new ReservationDetailsDto();

            dto.setReservationType(ReservationType.RESTAURANT_RESERVATION);
            dto.setReservationId(reservation.getReservationId());
            dto.setRelatedName(reservation.getRestaurantName());
            dto.setRelatedId(reservation.getRestaurantId());
            dto.setAgencyId(reservation.getAgencyId());
            dto.setFromDate(reservation.getFromDate());
            dto.setToDate(reservation.getToDate());

            response.add(dto);
        }

        return response;
    }

    @Override
    public void createRestaurant(CreateRestaurantDto request) {
        Restaurant restaurant = new Restaurant();
        AppUser restaurantManager = userService.getUserById(request.getManagerId());

        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setAddress(request.getAddress());
        restaurant.setPhone(request.getPhone());
        restaurant.setImageUrl(request.getImageUrl());
        restaurant.setAvailableTables(request.getAvailableTables());
        restaurant.setManager(restaurantManager);

        restaurantManager.setRestaurant(restaurant);

        restaurantRepository.save(restaurant);

        restaurantManager.setRestaurant(restaurant);
    }

    @Override
    public void updateRestaurant(RestaurantDto request) {
        Restaurant restaurant = restaurantRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with id: " + request.getId() + " not found"));

        restaurant = mapDtoToRestaurant(request);

        restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Integer id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with id: " + id + " not found"));

        restaurantRepository.delete(restaurant);
    }

    private static Restaurant mapDtoToRestaurant(RestaurantDto dto) {
        Restaurant restaurant = new Restaurant();

        restaurant.setId(dto.getId());
        restaurant.setName(dto.getName());
        restaurant.setDescription(dto.getDescription());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhone(dto.getPhone());
        restaurant.setImageUrl(dto.getImageUrl());
        restaurant.setAvailableTables(dto.getAvailableTables());
//        restaurant.setManager(
//
//        );

        return restaurant;
    }

    private static RestaurantDto mapRestaurantToDto(Restaurant restaurant) {
        RestaurantDto dto = new RestaurantDto();

        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        dto.setAddress(restaurant.getAddress());
        dto.setPhone(restaurant.getPhone());
        dto.setImageUrl(restaurant.getImageUrl());
        dto.setAvailableTables(restaurant.getAvailableTables());
        dto.setManagerId(restaurant.getManager().getId());

        return dto;
    }
}
