package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.Restaurant;
import com.spu.TourismApp.Repositories.RestaurantRepository;
import com.spu.TourismApp.Shared.Dto.CreateTouristAttractionDto;
import com.spu.TourismApp.Shared.Dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAllRestaurants();
    }

    @Override
    public RestaurantDto getRestaurantDto(Integer id) {
        return null;
    }

    @Override
    public Restaurant getRestaurant(Integer id){
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with id: " + id + " not found"));
    }

    @Override
    public void createRestaurant(CreateTouristAttractionDto request) {

    }

    @Override
    public void updateRestaurant(RestaurantDto request) {

    }

    @Override
    public void deleteRestaurant(Integer id) {

    }
}
