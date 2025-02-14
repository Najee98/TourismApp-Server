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
@RequiredArgsConstructor // Lombok annotation to generate a constructor with required fields (final fields).
public class TourController {

    private final TourService tourService; // Dependency injection for TourService.

    /**
     * Retrieves a list of all available tours.
     *
     * @return ResponseEntity containing a list of TourDto objects.
     */
    @GetMapping
//    @PreAuthorize("hasAnyAuthority('tours:viewTours')") // Ensures only authorized users can access this endpoint.
    public ResponseEntity<List<TourDto>> getAllTours() {
        return ResponseEntity.ok(tourService.getAllTours());
    }

    /**
     * Retrieves tour details by tour ID.
     *
     * @param id The ID of the tour.
     * @return ResponseEntity containing the tour details.
     */
    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('tours:getTour')")
    public ResponseEntity<TourDto> getTourById(@PathVariable Integer id) {
        return ResponseEntity.ok(tourService.getTourById(id));
    }

    /**
     * Creates a new tour.
     *
     * @param request The CreateTourDto containing tour details.
     * @return ResponseEntity with a success message and HTTP 201 status.
     */
    @PostMapping
//    @PreAuthorize("hasAnyAuthority('tours:createTour')")
    public ResponseEntity<Object> createTour(@RequestBody CreateTourDto request) {
        tourService.createTour(request);
        return new ResponseEntity<>("{ \"message\": \" created successfully  \" }", HttpStatus.CREATED);
    }

    /**
     * Updates an existing tour.
     *
     * @param id      The ID of the tour to update.
     * @param request The CreateTourDto containing updated details.
     * @return ResponseEntity with a success message and HTTP 200 status.
     */
    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('tours:updateTour')")
    public ResponseEntity<Object> updateTour(@PathVariable Integer id, @RequestBody CreateTourDto request) {
        tourService.updateTour(id, request);
        return new ResponseEntity<>("{ \"message\": \" updated successfully  \" }", HttpStatus.OK);
    }

    /**
     * Deletes a tour by its ID.
     *
     * @param id The ID of the tour to delete.
     * @return ResponseEntity with a success message and HTTP 204 status.
     */
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('tours:deleteTour')")
    public ResponseEntity<Object> deleteTour(@PathVariable Integer id) {
        tourService.deleteTour(id);
        return new ResponseEntity<>("{ \"message\": \" deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }

    /**
     * Adds a user to a tour.
     *
     * @param tourId The ID of the tour.
     * @return ResponseEntity with a success message and HTTP 200 status.
     */
    @PostMapping("/users/add")
//    @PreAuthorize("hasAnyAuthority('tours:addUserToTour')")
    public ResponseEntity<Object> addUserToTour(@RequestParam Integer tourId){
        tourService.addUserToTour(tourId);
        return new ResponseEntity<>("{ \"message\": \" user is now added to the tour.  \" }", HttpStatus.OK);
    }

    /**
     * Removes a user from a tour.
     *
     * @param tourId The ID of the tour.
     * @return ResponseEntity with a success message and HTTP 200 status.
     */
    @PostMapping("/users/remove")
//    @PreAuthorize("hasAnyAuthority('tours:removeUserFromTour')")
    public ResponseEntity<Object> removeUserFromTour(@RequestParam Integer tourId){
        tourService.removeUserFromTour(tourId);
        return new ResponseEntity<>("{ \"message\": \" user is now removed from the tour.  \" }", HttpStatus.OK);
    }

    /**
     * Retrieves all tours that the currently authenticated user is associated with.
     *
     * @return ResponseEntity containing a list of TourDto objects.
     */
    @GetMapping("/user-tours")
//    @PreAuthorize("hasAnyAuthority('tours:viewUserTours')")
    public ResponseEntity<List<TourDto>> getAllUserTours() {
        return new ResponseEntity<>(tourService.getAllUserTours(), HttpStatus.OK);
    }

    /**
     * Retrieves reservations for a specific tour.
     *
     * @param tourId The ID of the tour.
     * @return ResponseEntity containing a list of ReservationDetailsDto objects.
     */
    @GetMapping("/reservations")
//    @PreAuthorize("hasAnyAuthority('tours:viewTourReservations')")
    public ResponseEntity<List<ReservationDetailsDto>> getTourReservations(@RequestParam Integer tourId){
        return new ResponseEntity<>(tourService.getTourReservations(tourId), HttpStatus.OK);
    }
}
