package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Services.ReservationService;
import com.spu.TourismApp.Shared.Dto.Reservation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReservationDetailsDto> getReservationById(@PathVariable Integer id) {
        ReservationDetailsDto reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping()
    public ResponseEntity<Object> createReservation(@RequestBody ReservationDto request) {
        reservationService.createReservation(request);
        return new ResponseEntity<>("{ \"message\": \" created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReservation(
            @PathVariable Integer id, 
            @RequestBody ReservationDto request) {

        reservationService.updateReservation(id, request);
        return new ResponseEntity<>("{ \"message\": \" updated successfully  \" }", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>("{ \"message\": \" deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }
}
