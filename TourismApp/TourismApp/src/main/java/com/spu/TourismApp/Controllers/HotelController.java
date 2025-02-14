package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.HotelService;
import com.spu.TourismApp.Services.UserService;
import com.spu.TourismApp.Shared.Dto.Agency.ManagementUserDto;
import com.spu.TourismApp.Shared.Dto.Hotel.CreateHotelDto;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelDto;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelReservationDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final UserService userService;

    @Autowired
    public HotelController(HotelService hotelService, UserService userService) {
        this.hotelService = hotelService;
        this.userService = userService;
    }

    @GetMapping
//    @PreAuthorize("hasAnyAuthority('hotels:viewHotels')")
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{hotelId}")
//    @PreAuthorize("hasAnyAuthority('hotels:getHotel')")
    public ResponseEntity<HotelDto> getHotelDetails(@PathVariable Integer hotelId) {
        return ResponseEntity.ok(hotelService.getHotelDetails(hotelId));
    }

    @GetMapping("/reservations")
//    @PreAuthorize("hasAnyAuthority('hotels:getAllHotelReservations')")
    public ResponseEntity<List<ReservationDetailsDto>> getHotelReservations() {
        return new ResponseEntity<>(hotelService.getHotelReservations(), HttpStatus.OK);
    }

    @PostMapping
//    @PreAuthorize("hasAnyAuthority('hotels:createHotel')")
    public ResponseEntity<Object> createHotel(@RequestBody CreateHotelDto request) {
        hotelService.createHotel(request);
        return new ResponseEntity<>("{ \"message\": \" Created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping()
//    @PreAuthorize("hasAnyAuthority('hotels:updateHotel')")
    public ResponseEntity<String> updateHotel(@RequestBody HotelDto request) {
        hotelService.updateHotel(request);
        return new ResponseEntity<>("{ \"message\": \" Updated successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{hotelId}")
//    @PreAuthorize("hasAnyAuthority('hotels:deleteHotel')")
    public ResponseEntity<String> deleteHotel(@PathVariable Integer hotelId) {
        hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>("{ \"message\": \" Deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users")
//    @PreAuthorize("hasAnyAuthority('hotels:getAllUsersForHotel')")
    public ResponseEntity<List<ManagementUserDto>> getAllUsersForHotels(){
        return new ResponseEntity<>(userService.getAllUsersForHotels(), HttpStatus.OK);
    }

}
