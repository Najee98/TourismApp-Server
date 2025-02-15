package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.ReservationService;
import com.spu.TourismApp.Shared.Dto.Reservation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired // Injects the ReservationService dependency into the controller.
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Retrieves reservation details by reservation ID.
     *
     * @param id The ID of the reservation to retrieve.
     * @return ResponseEntity containing the reservation details.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('reservations:getReservation')") // Restricts access based on authority.
    public ResponseEntity<ReservationDetailsDto> getReservationById(@PathVariable Integer id) {
        ReservationDetailsDto reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    /**
     * Creates a new reservation.
     *
     * @param request The ReservationDto containing reservation details.
     * @return ResponseEntity with a success message and HTTP 201 status.
     */
    @PostMapping()
    @PreAuthorize("hasAnyAuthority('reservations:createReservation')")
    public ResponseEntity<Object> createReservation(@RequestBody ReservationDto request) {
        reservationService.createReservation(request);
        return new ResponseEntity<>("{ \"message\": \" created successfully  \" }", HttpStatus.CREATED);
    }

    /**
     * Updates an existing reservation.
     *
     * @param id      The ID of the reservation to update.
     * @param request The ReservationDto containing updated reservation details.
     * @return ResponseEntity with a success message and HTTP 200 status.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('reservations:updateReservation')")
    public ResponseEntity<Object> updateReservation(
            @PathVariable Integer id,
            @RequestBody ReservationDto request) {

        reservationService.updateReservation(id, request);
        return new ResponseEntity<>("{ \"message\": \" updated successfully  \" }", HttpStatus.OK);
    }

    /**
     * Deletes a reservation by its ID.
     *
     * @param id The ID of the reservation to delete.
     * @return ResponseEntity with a success message and HTTP 204 status.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('reservations:deleteReservation')")
    public ResponseEntity<Object> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>("{ \"message\": \" deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }
}
