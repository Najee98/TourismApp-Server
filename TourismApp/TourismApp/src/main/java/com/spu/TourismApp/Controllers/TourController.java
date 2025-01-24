package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Models.Tour;
import com.spu.TourismApp.Services.TourService;
import com.spu.TourismApp.Shared.Dto.Tour.CreateTourDto;
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping
    public ResponseEntity<List<TourDto>> getAllTours() {
        return ResponseEntity.ok(tourService.getAllTours());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDto> getTourById(@PathVariable Integer id) {
        return ResponseEntity.ok(tourService.getTourById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createTour(@RequestBody CreateTourDto request) {
        tourService.createTour(request);
        return new ResponseEntity<>("{ \"message\": \" created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTour(@PathVariable Integer id, @RequestBody CreateTourDto request) {
        tourService.updateTour(id, request);
        return new ResponseEntity<>("{ \"message\": \" updated successfully  \" }", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTour(@PathVariable Integer id) {
        tourService.deleteTour(id);
        return new ResponseEntity<>("{ \"message\": \" deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/add")
    public ResponseEntity<Object> addUserToTour(@RequestParam Integer tourId){
        tourService.addUserToTour(tourId);
        return new ResponseEntity<>("{ \"message\": \" user is now added to the tour.  \" }", HttpStatus.OK);
    }

    @GetMapping("/user-tours")
    public ResponseEntity<List<TourDto>> getAllUserTours() {
        return new ResponseEntity<>(tourService.getAllUserTours(), HttpStatus.OK);
    }
}
