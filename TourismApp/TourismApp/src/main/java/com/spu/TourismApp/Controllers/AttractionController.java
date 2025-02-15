package com.spu.TourismApp.Controllers;

import com.spu.TourismApp.Services.AttractionService;
import com.spu.TourismApp.Shared.Dto.Attraction.CreateAttractionDto;
import com.spu.TourismApp.Shared.Dto.Attraction.AttractionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController // Marks this class as a REST controller to handle HTTP requests.
@RequestMapping("/api/attractions") // Sets the base URL for all endpoints in this controller.
public class AttractionController {

    private final AttractionService attractionService;

    /**
     * Constructor-based dependency injection for AttractionService.
     *
     * @param attractionService Service responsible for managing tourist attractions.
     */
    @Autowired
    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    /**
     * Retrieves a list of all tourist attractions.
     *
     * @return ResponseEntity containing a list of AttractionDto objects.
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('attractions:viewAttractions')") // Access control for viewing attractions.
    public ResponseEntity<List<AttractionDto>> getAllAttractions() {
        return ResponseEntity.ok(attractionService.getAllTouristAttractions());
    }

    /**
     * Retrieves details of a specific tourist attraction by its ID.
     *
     * @param attractionId The ID of the attraction.
     * @return ResponseEntity containing the AttractionDto object.
     */
    @GetMapping("/{attractionId}")
    @PreAuthorize("hasAnyAuthority('attractions:getAttractions')") // Access control for fetching attraction details.
    public ResponseEntity<AttractionDto> getAttractionById(@PathVariable Integer attractionId) {
        return ResponseEntity.ok(attractionService.getTouristAttractionDetails(attractionId));
    }

    /**
     * Creates a new tourist attraction.
     *
     * @param request DTO containing details required for attraction creation.
     * @return ResponseEntity with a success message and HTTP status 201 (Created).
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('attractions:createAttraction')") // Access control for creating attractions.
    public ResponseEntity<String> createAttraction(@RequestBody CreateAttractionDto request) {
        attractionService.createTouristAttraction(request);
        return new ResponseEntity<>("{ \"message\": \"Created successfully\" }", HttpStatus.CREATED);
    }

    /**
     * Updates an existing tourist attraction.
     *
     * @param request DTO containing updated attraction details.
     * @return ResponseEntity with a success message and HTTP status 204 (No Content).
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('attractions:updateAttraction')") // Access control for updating attractions.
    public ResponseEntity<String> updateAttraction(@RequestBody AttractionDto request) {
        attractionService.updateTouristAttraction(request);
        return new ResponseEntity<>("{ \"message\": \"Updated successfully\" }", HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes a tourist attraction by its ID.
     *
     * @param id The ID of the attraction to be deleted.
     * @return ResponseEntity with a success message and HTTP status 204 (No Content).
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('attractions:deleteAttraction')") // Access control for deleting attractions.
    public ResponseEntity<String> deleteAttraction(@PathVariable Integer id) {
        attractionService.deleteTouristAttraction(id);
        return new ResponseEntity<>("{ \"message\": \"Deleted successfully\" }", HttpStatus.NO_CONTENT);
    }
}

