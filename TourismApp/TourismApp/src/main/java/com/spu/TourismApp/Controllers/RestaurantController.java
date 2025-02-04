package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.RestaurantService;
import com.spu.TourismApp.Services.UserService;
import com.spu.TourismApp.Shared.Dto.Agency.ManagementUserDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.CreateRestaurantDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
 //   @PreAuthorize("hasAuthority('restaurants:viewRestaurants')")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{restaurantId}")
  //  @PreAuthorize("hasAuthority('restaurants:getRestaurant')")
    public ResponseEntity<RestaurantDto> getRestaurantDetails(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantDetails(restaurantId));
    }

    @GetMapping("/reservations")
  //  @PreAuthorize("hasAuthority('restaurants:getAllRestaurantReservations')")
    public ResponseEntity<List<RestaurantReservationDto>> getRestaurantReservations() {
        return new ResponseEntity<>(restaurantService.getRestaurantReservations(), HttpStatus.OK);
    }

    @PostMapping
  //  @PreAuthorize("hasAuthority('restaurants:createRestaurant')")
    public ResponseEntity<Object> createRestaurant(@RequestBody CreateRestaurantDto request) {
        restaurantService.createRestaurant(request);
        return new ResponseEntity<>("{ \"message\": \" Created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping()
  //  @PreAuthorize("hasAuthority('restaurants:updateRestaurant')")
    public ResponseEntity<Object> updateRestaurant(@RequestBody RestaurantDto request) {
        restaurantService.updateRestaurant(request);
        return new ResponseEntity<>("{ \"message\": \" Updated successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{restaurantId}")
  //  @PreAuthorize("hasAuthority('restaurants:deleteRestaurant')")
    public ResponseEntity<Object> deleteRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>("{ \"message\": \" Deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users")
 //   @PreAuthorize("hasAuthority('restaurants:getAllUsersForRestaurant')")
    public ResponseEntity<List<ManagementUserDto>> getAllUsersForRestaurants(){
        return new ResponseEntity<>(userService.getAllUsersForRestaurants(), HttpStatus.OK);
    }
}
