package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.AgencyService;
import com.spu.TourismApp.Services.UserService;
import com.spu.TourismApp.Shared.Dto.Agency.ManagementUserDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyTourDto;
import com.spu.TourismApp.Shared.Dto.Agency.CreateAgencyDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {

    private final AgencyService agencyService;
    private final UserService userService;

    @Autowired
    public AgencyController(AgencyService agencyService, UserService userService) {
        this.agencyService = agencyService;
        this.userService = userService;
    }

    @GetMapping
    //@PreAuthorize("hasAuthority('agencies:viewAgencies')")
    public ResponseEntity<List<AgencyDto>> getAllAgencies() {
        return ResponseEntity.ok(agencyService.getAllAgencies());
    }

    @GetMapping("/{agencyId}")
    //@PreAuthorize("hasAuthority('agencies:getAgency')")
    public ResponseEntity<AgencyDto> getAgencyById(@PathVariable Integer agencyId) {
        return ResponseEntity.ok(agencyService.getAgency(agencyId));
    }

    @GetMapping("/tours")
    //@PreAuthorize("hasAuthority('agencies:viewAgencyTours')")
    public ResponseEntity<List<AgencyTourDto>> getAgencyTours() {
        return new ResponseEntity<>(agencyService.getAgencyTours(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('agencies:createAgency')")
    public ResponseEntity<String> createAgency(@RequestBody CreateAgencyDto request) {
        agencyService.createAgency(request);
        return new ResponseEntity<>("{ \"message\": \" Created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('agencies:updateAgency')")
    public ResponseEntity<String> updateAgency(@RequestBody AgencyDto request) {
        agencyService.updateAgency(request);
        return new ResponseEntity<>("{ \"message\": \" Updated successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('agencies:deleteAgency')")
    public ResponseEntity<String> deleteAgency(@PathVariable Integer id) {
        agencyService.deleteAgency(id);
        return new ResponseEntity<>("{ \"message\": \" Deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reservations")
    @PreAuthorize("hasAnyAuthority('agencies:viewAgencyReservations')")
    public ResponseEntity<List<ReservationDetailsDto>> getAgencyReservations() {
        return new ResponseEntity<>(agencyService.getAgencyReservations(), HttpStatus.OK);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('agencies:getAllUsersForAgencies')")
    public ResponseEntity<List<ManagementUserDto>> getAllUsersForAgencies(){
        return new ResponseEntity<>(userService.getAllUsersForAgencies(), HttpStatus.OK);
    }
}
