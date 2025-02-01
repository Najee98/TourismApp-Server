package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Restaurant;
import com.spu.TourismApp.Shared.Dto.Restaurant.CreateRestaurantDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantReservationDto;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDto> getAllRestaurants();

    RestaurantDto getRestaurantDetails(Integer id);

    Restaurant getRestaurant(Integer id);

    void createRestaurant(CreateRestaurantDto request);

    void updateRestaurant(RestaurantDto request);

    void deleteRestaurant(Integer id);

    List<RestaurantReservationDto> getRestaurantReservations();
}
