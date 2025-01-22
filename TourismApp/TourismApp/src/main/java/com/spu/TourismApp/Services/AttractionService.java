package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Attraction;
import com.spu.TourismApp.Shared.Dto.Attraction.CreateAttractionDto;
import com.spu.TourismApp.Shared.Dto.Attraction.AttractionDto;

import java.util.List;

public interface AttractionService {

    List<AttractionDto> getAllTouristAttractions();

    AttractionDto getTouristAttractionDetails(Integer id);

    Attraction getTouristAttraction(Integer id);

    void createTouristAttraction(CreateAttractionDto request);

    void updateTouristAttraction(AttractionDto request);

    void deleteTouristAttraction(Integer id);
}
