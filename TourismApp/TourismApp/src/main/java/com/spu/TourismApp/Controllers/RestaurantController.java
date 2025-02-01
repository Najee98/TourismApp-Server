package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.RestaurantService;
import com.spu.TourismApp.Services.UserService;
import com.spu.TourismApp.Shared.Dto.Agency.ManagementUserDto;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelReservationDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.CreateRestaurantDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurantDetails(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantDetails(restaurantId));
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<RestaurantReservationDto>> getHotelReservations() {
        return new ResponseEntity<>(restaurantService.getRestaurantReservations(), HttpStatus.OK);
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

    @GetMapping("/users")
    public ResponseEntity<List<ManagementUserDto>> getAllUsersForAgencies(){
        return new ResponseEntity<>(userService.getAllUsersForRestaurants(), HttpStatus.OK);
    }
}
