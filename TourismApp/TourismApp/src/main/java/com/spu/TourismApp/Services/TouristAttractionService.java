package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.TouristAttraction;
import com.spu.TourismApp.Shared.Dto.CreateTouristAttractionDto;
import com.spu.TourismApp.Shared.Dto.TouristAttractionDto;

import java.util.List;

public interface TouristAttractionService {

    List<TouristAttractionDto> getAllTouristAttractions();

    TouristAttractionDto getTouristAttractionDto(Integer id);

    TouristAttraction getTouristAttraction(Integer id);

    void createTouristAttraction(CreateTouristAttractionDto request);

    void updateTouristAttraction(TouristAttractionDto request);

    void deleteTouristAttraction(int id);
}
