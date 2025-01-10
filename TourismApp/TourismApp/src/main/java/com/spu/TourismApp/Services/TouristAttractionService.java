package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.TouristAttraction;
import com.spu.TourismApp.Shared.Dto.TouristAttraction.CreateTouristAttractionDto;
import com.spu.TourismApp.Shared.Dto.TouristAttraction.TouristAttractionDto;

import java.util.List;

public interface TouristAttractionService {

    List<TouristAttractionDto> getAllTouristAttractions();

    TouristAttractionDto getTouristAttractionDetails(Integer id);

    TouristAttraction getTouristAttraction(Integer id);

    void createTouristAttraction(CreateTouristAttractionDto request);

    void updateTouristAttraction(TouristAttractionDto request);

    void deleteTouristAttraction(Integer id);
}
