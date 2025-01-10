package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.RestaurantService;
import com.spu.TourismApp.Shared.Dto.CreateHotelDto;
import com.spu.TourismApp.Shared.Dto.CreateRestaurantDto;
import com.spu.TourismApp.Shared.Dto.HotelDto;
import com.spu.TourismApp.Shared.Dto.RestaurantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurantDetails(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantDetails(restaurantId));
    }

    @PostMapping
    public ResponseEntity<Object> createRestaurant(@RequestBody CreateRestaurantDto request) {
        restaurantService.createRestaurant(request);
        return new ResponseEntity<>("{ \"message\": \" Created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Object> updateRestaurant(@RequestBody RestaurantDto request) {
        restaurantService.updateRestaurant(request);
        return new ResponseEntity<>("{ \"message\": \" Updated successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Object> deleteRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>("{ \"message\": \" Deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }
}
