package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.TouristAttraction;
import com.spu.TourismApp.Repositories.TouristAttractionRepository;
import com.spu.TourismApp.Shared.Dto.CreateTouristAttractionDto;
import com.spu.TourismApp.Shared.Dto.TouristAttractionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristAttractionServiceImpl implements TouristAttractionService{

    private final TouristAttractionRepository attractionRepository;

    @Autowired
    public TouristAttractionServiceImpl(TouristAttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    @Override
    public List<TouristAttractionDto> getAllTouristAttractions() {
        return attractionRepository.findAllAttractions();
    }

    @Override
    public TouristAttractionDto getTouristAttractionDto(Integer id) {
        return attractionRepository.findAttractionById(id);
    }

    @Override
    public TouristAttraction getTouristAttraction(Integer id) {
        return attractionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist Attraction Not Found"));
    }

    @Override
    public void createTouristAttraction(CreateTouristAttractionDto request) {
        TouristAttraction attraction = new TouristAttraction();

        attraction.setName(request.getName());
        attraction.setAddress(request.getAddress());
        attraction.setPhone(request.getPhone());
        attraction.setImageUrl(request.getImageUrl());

        attractionRepository.save(attraction);
    }

    @Override
    public void updateTouristAttraction(TouristAttractionDto request) {
        TouristAttraction attraction = attractionRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tourist Attraction Not Found"));

        attraction.setName(request.getName());
        attraction.setAddress(request.getAddress());
        attraction.setPhone(request.getPhone());
        attraction.setImageUrl(request.getImageUrl());

    }

    @Override
    public void deleteTouristAttraction(int id) {
        TouristAttraction attraction = attractionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist Attraction Not Found"));

        attractionRepository.delete(attraction);
    }
}
