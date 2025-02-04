package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.TourService;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Tour.CreateTourDto;
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping
    @PreAuthorize("hasAuthority('tours:viewTours')")
    public ResponseEntity<List<TourDto>> getAllTours() {
        return ResponseEntity.ok(tourService.getAllTours());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('tours:getTour')")
    public ResponseEntity<TourDto> getTourById(@PathVariable Integer id) {
        return ResponseEntity.ok(tourService.getTourById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('tours:createTour')")
    public ResponseEntity<Object> createTour(@RequestBody CreateTourDto request) {
        tourService.createTour(request);
        return new ResponseEntity<>("{ \"message\": \" created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('tours:updateTour')")
    public ResponseEntity<Object> updateTour(@PathVariable Integer id, @RequestBody CreateTourDto request) {
        tourService.updateTour(id, request);
        return new ResponseEntity<>("{ \"message\": \" updated successfully  \" }", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('tours:deleteTour')")
    public ResponseEntity<Object> deleteTour(@PathVariable Integer id) {
        tourService.deleteTour(id);
        return new ResponseEntity<>("{ \"message\": \" deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/add")
    @PreAuthorize("hasAuthority('tours:addUserToTour')")
    public ResponseEntity<Object> addUserToTour(@RequestParam Integer tourId){
        tourService.addUserToTour(tourId);
        return new ResponseEntity<>("{ \"message\": \" user is now added to the tour.  \" }", HttpStatus.OK);
    }

    @PostMapping("/users/remove")
    @PreAuthorize("hasAuthority('tours:removeUserFromTour')")
    public ResponseEntity<Object> removeUserFromTour(@RequestParam Integer tourId){
        tourService.removeUserFromTour(tourId);
        return new ResponseEntity<>("{ \"message\": \" user is now removed from the tour.  \" }", HttpStatus.OK);
    }

    @GetMapping("/user-tours")
    @PreAuthorize("hasAuthority('tours:viewUserTours')")
    public ResponseEntity<List<TourDto>> getAllUserTours() {
        return new ResponseEntity<>(tourService.getAllUserTours(), HttpStatus.OK);
    }

    @GetMapping("/reservations")
    @PreAuthorize("hasAuthority('tours:viewTourReservations')")
    public ResponseEntity<List<ReservationDetailsDto>> getTourReservations(@RequestParam Integer tourId){
        return new ResponseEntity<>(tourService.getTourReservations(tourId), HttpStatus.OK);
    }
}
