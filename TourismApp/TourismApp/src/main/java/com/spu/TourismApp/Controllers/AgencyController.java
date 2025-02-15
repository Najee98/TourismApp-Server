package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.AgencyService;
import com.spu.TourismApp.Services.UserService;
import com.spu.TourismApp.Shared.Dto.Agency.ManagementUserDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyTourDto;
import com.spu.TourismApp.Shared.Dto.Agency.CreateAgencyDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a RESTful web controller that handles HTTP requests.
@RequestMapping("/api/agencies") // Defines the base URL for all endpoints in this controller.
public class AgencyController {

    private final AgencyService agencyService;
    private final UserService userService;

    /**
     * Constructor-based dependency injection for AgencyService and UserService.
     *
     * @param agencyService Service handling agency-related operations.
     * @param userService Service handling user-related operations.
     */
    @Autowired
    public AgencyController(AgencyService agencyService, UserService userService) {
        this.agencyService = agencyService;
        this.userService = userService;
    }

    /**
     * Retrieves a list of all agencies.
     *
     * @return ResponseEntity containing a list of AgencyDto objects.
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('agencies:viewAgencies')") // Restricts access based on user permissions.
    public ResponseEntity<List<AgencyDto>> getAllAgencies() {
        return ResponseEntity.ok(agencyService.getAllAgencies());
    }

    /**
     * Retrieves an agency by its ID.
     *
     * @param agencyId The ID of the agency.
     * @return ResponseEntity containing the AgencyDto object.
     */
    @GetMapping("/{agencyId}")
    @PreAuthorize("hasAnyAuthority('agencies:getAgency')")
    public ResponseEntity<AgencyDto> getAgencyById(@PathVariable Integer agencyId) {
        return ResponseEntity.ok(agencyService.getAgency(agencyId));
    }

    /**
     * Retrieves all tours associated with the current agency.
     *
     * @return ResponseEntity containing a list of TourDto objects.
     */
    @GetMapping("/tours")
    @PreAuthorize("hasAnyAuthority('agencies:viewAgencyTours')")
    public ResponseEntity<List<TourDto>> getAgencyTours() {
        return new ResponseEntity<>(agencyService.getAgencyTours(), HttpStatus.OK);
    }

    /**
     * Creates a new agency.
     *
     * @param request DTO containing agency creation details.
     * @return ResponseEntity with a success message and HTTP status 201 (Created).
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('agencies:createAgency')")
    public ResponseEntity<String> createAgency(@RequestBody CreateAgencyDto request) {
        agencyService.createAgency(request);
        return new ResponseEntity<>("{ \"message\": \"Created successfully\" }", HttpStatus.CREATED);
    }

    /**
     * Updates an existing agency.
     *
     * @param request DTO containing updated agency details.
     * @return ResponseEntity with a success message and HTTP status 204 (No Content).
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('agencies:updateAgency')")
    public ResponseEntity<String> updateAgency(@RequestBody AgencyDto request) {
        agencyService.updateAgency(request);
        return new ResponseEntity<>("{ \"message\": \"Updated successfully\" }", HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes an agency by its ID.
     *
     * @param id The ID of the agency to delete.
     * @return ResponseEntity with a success message and HTTP status 204 (No Content).
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('agencies:deleteAgency')")
    public ResponseEntity<String> deleteAgency(@PathVariable Integer id) {
        agencyService.deleteAgency(id);
        return new ResponseEntity<>("{ \"message\": \"Deleted successfully\" }", HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves reservations for the current agency.
     *
     * @return ResponseEntity containing a list of ReservationDetailsDto objects.
     */
    @GetMapping("/reservations")
    @PreAuthorize("hasAnyAuthority('agencies:viewAgencyReservations')")
    public ResponseEntity<List<ReservationDetailsDto>> getAgencyReservations() {
        return new ResponseEntity<>(agencyService.getAgencyReservations(), HttpStatus.OK);
    }

    /**
     * Retrieves all users associated with agencies.
     *
     * @return ResponseEntity containing a list of ManagementUserDto objects.
     */
    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('agencies:getAllUsersForAgencies')")
    public ResponseEntity<List<ManagementUserDto>> getAllUsersForAgencies() {
        return new ResponseEntity<>(userService.getAllUsersForAgencies(), HttpStatus.OK);
    }
}
