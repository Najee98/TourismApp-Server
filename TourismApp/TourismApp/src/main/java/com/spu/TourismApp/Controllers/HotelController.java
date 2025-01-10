package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.HotelService;
import com.spu.TourismApp.Shared.Dto.Hotel.CreateHotelDto;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelDto;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelDetails(@PathVariable Integer hotelId) {
        return ResponseEntity.ok(hotelService.getHotelDetails(hotelId));
    }

    @GetMapping("/hotel-reservations")
    public ResponseEntity<List<HotelReservationDto>> getHotelReservations(@RequestParam Integer hotelId) {
        return new ResponseEntity<>(hotelService.getHotelReservations(hotelId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createHotel(@RequestBody CreateHotelDto request) {
        hotelService.createHotel(request);
        return new ResponseEntity<>("{ \"message\": \" Created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<String> updateHotel(@RequestBody HotelDto request) {
        hotelService.updateHotel(request);
        return new ResponseEntity<>("{ \"message\": \" Updated successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable Integer hotelId) {
        hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>("{ \"message\": \" Deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }

}
