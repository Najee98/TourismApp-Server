package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Services.ReservationService;
import com.spu.TourismApp.Shared.Dto.CreateReservationDto;
import com.spu.TourismApp.Shared.Dto.ReservationDto;
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

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllUserReservations() {
        List<ReservationDto> reservations = reservationService.getAllUserReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Integer id) {
        ReservationDto reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping()
    public ResponseEntity<Reservation> createReservationByUser(@RequestBody CreateReservationDto request) {
        return new ResponseEntity<>(reservationService.createReservation(request), HttpStatus.CREATED);
    }

//    @PostMapping("/agency")
//    public ResponseEntity<Reservation> createReservationByAgency(@RequestBody CreateReservationDto request) {
//        return new ResponseEntity<>(reservationService.createReservationByAgency(request), HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable Integer id, 
            @RequestBody CreateReservationDto request) {

        return new ResponseEntity<>(reservationService.updateReservation(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>("{ \"message\": \" deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }
}
