package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.TravellingAgencyService;
import com.spu.TourismApp.Shared.Dto.TravellingAgency.CreateTravellingAgencyDto;
import com.spu.TourismApp.Shared.Dto.TravellingAgency.TravellingAgencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
public class TravellingAgencyController {

    private final TravellingAgencyService agencyService;

    @Autowired
    public TravellingAgencyController(TravellingAgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping
    public ResponseEntity<List<TravellingAgencyDto>> getAllAgencies() {
        return ResponseEntity.ok(agencyService.getAllTravellingAgencies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravellingAgencyDto> getAgencyById(@PathVariable Integer id) {
        return ResponseEntity.ok(agencyService.getTravellingAgency(id));
    }

//    @GetMapping("/{id}/attractions")
//    public ResponseEntity<List<TouristAttractionDto>> getAllTravellingAgencies(@PathVariable Integer id) {
//        return new ResponseEntity<>(agencyService.getAgencyAttractions(id), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<String> createAgency(@RequestBody CreateTravellingAgencyDto request) {
        agencyService.createTravellingAgency(request);
        return new ResponseEntity<>("{ \"message\": \" Created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateAgency(@RequestBody TravellingAgencyDto request) {
        agencyService.updateTravellingAgency(request);
        return new ResponseEntity<>("{ \"message\": \" Updated successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgency(@PathVariable Integer id) {
        agencyService.deleteTravellingAgency(id);
        return new ResponseEntity<>("{ \"message\": \" Deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }
}
