package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Restaurant;
import com.spu.TourismApp.Shared.Dto.CreateRestaurantDto;
import com.spu.TourismApp.Shared.Dto.CreateTouristAttractionDto;
import com.spu.TourismApp.Shared.Dto.HotelDto;
import com.spu.TourismApp.Shared.Dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDto> getAllRestaurants();

    RestaurantDto getRestaurantDetails(Integer id);

    Restaurant getRestaurant(Integer id);

    void createRestaurant(CreateRestaurantDto request);

    void updateRestaurant(RestaurantDto request);

    void deleteRestaurant(Integer id);
}
