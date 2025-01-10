package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.TouristAttraction;
import com.spu.TourismApp.Repositories.TouristAttractionRepository;
import com.spu.TourismApp.Shared.Dto.TouristAttraction.CreateTouristAttractionDto;
import com.spu.TourismApp.Shared.Dto.TouristAttraction.TouristAttractionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<TouristAttraction> touristAttractions = attractionRepository.findAll();

        List<TouristAttractionDto> response = new ArrayList<>();

        for (TouristAttraction attraction : touristAttractions) {
            TouristAttractionDto dto = new TouristAttractionDto();

            dto = mapAttractionToDto(attraction);

            response.add(dto);
        }

        return response;
    }

    @Override
    public TouristAttractionDto getTouristAttractionDetails(Integer id) {
        TouristAttraction attraction = attractionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attraction with id: " + id + " not found"));

        TouristAttractionDto response = new TouristAttractionDto();

        response = mapAttractionToDto(attraction);

        return response;
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
        attraction.setDescription(request.getDescription());
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
        attraction.setDescription(request.getDescription());
        attraction.setPhone(request.getPhone());
        attraction.setImageUrl(request.getImageUrl());

        attractionRepository.save(attraction);
    }

    @Override
    public void deleteTouristAttraction(Integer id) {
        TouristAttraction attraction = attractionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist Attraction Not Found"));

        attractionRepository.delete(attraction);
    }

    private static TouristAttractionDto mapAttractionToDto(TouristAttraction attraction) {
        TouristAttractionDto dto = new TouristAttractionDto();

        dto.setId(attraction.getId());
        dto.setName(attraction.getName());
        dto.setDescription(attraction.getDescription());
        dto.setAddress(attraction.getAddress());
        dto.setPhone(attraction.getPhone());
        dto.setImageUrl(attraction.getImageUrl());
        return dto;
    }

    private static TouristAttraction mapDtoToAttraction(TouristAttractionDto dto) {
        TouristAttraction attraction = new TouristAttraction();

        attraction.setId(dto.getId());
        attraction.setName(dto.getName());
        attraction.setDescription(dto.getDescription());
        attraction.setAddress(dto.getAddress());
        attraction.setPhone(dto.getPhone());
        attraction.setImageUrl(dto.getImageUrl());
        return attraction;
    }
}
