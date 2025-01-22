package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.AttractionService;
import com.spu.TourismApp.Shared.Dto.Attraction.CreateAttractionDto;
import com.spu.TourismApp.Shared.Dto.Attraction.AttractionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    @Autowired
    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping
    public ResponseEntity<List<AttractionDto>> getAllAttractions() {
        return ResponseEntity.ok(attractionService.getAllTouristAttractions());
    }

    @GetMapping("/{attractionId}")
    public ResponseEntity<AttractionDto> getAttractionById(@PathVariable Integer attractionId) {
        return ResponseEntity.ok(attractionService.getTouristAttractionDetails(attractionId));
    }

    @PostMapping
    public ResponseEntity<String> createAttraction(@RequestBody CreateAttractionDto request) {
        attractionService.createTouristAttraction(request);
        return new ResponseEntity<>("{ \"message\": \" Created successfully  \" }", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateAttraction(@RequestBody AttractionDto request) {
        attractionService.updateTouristAttraction(request);
        return new ResponseEntity<>("{ \"message\": \" Updated successfully  \" }", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttraction(@PathVariable Integer id) {
        attractionService.deleteTouristAttraction(id);
        return new ResponseEntity<>("{ \"message\": \" Deleted successfully  \" }", HttpStatus.NO_CONTENT);
    }

}
