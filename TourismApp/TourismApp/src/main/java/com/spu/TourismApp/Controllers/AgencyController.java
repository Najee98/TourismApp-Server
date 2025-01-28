package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.AgencyService;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyTourDto;
import com.spu.TourismApp.Shared.Dto.Agency.CreateAgencyDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {

    private final AgencyService agencyService;

    @Autowired
    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping
    public ResponseEntity<List<AgencyDto>> getAllAgencies() {
        return ResponseEntity.ok(agencyService.getAllAgencies());
    }

    @GetMapping("/{agencyId}")
    public ResponseEntity<AgencyDto> getAgencyById(@PathVariable Integer agencyId) {
        return ResponseEntity.ok(agencyService.getAgency(agencyId));
    }

    @GetMapping("/{agencyId}/tours")
    public ResponseEntity<List<AgencyTourDto>> getAgencyTours(@PathVariable Integer agencyId) {
        return new ResponseEntity<>(agencyService.getAgencyTours(agencyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createAgency(@RequestBody CreateAgencyDto request) {
        agencyService.createAgency(request);
        return new ResponseEntity<>("{ \"message\": \" Created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateAgency(@RequestBody AgencyDto request) {
        agencyService.updateAgency(request);
        return new ResponseEntity<>("{ \"message\": \" Updated successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgency(@PathVariable Integer id) {
        agencyService.deleteAgency(id);
        return new ResponseEntity<>("{ \"message\": \" Deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDetailsDto>> getAgencyReservations(@RequestParam Integer agencyId) {
        return new ResponseEntity<>(agencyService.getAgencyReservations(agencyId), HttpStatus.OK);
    }
}
