package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.Attraction;
import com.spu.TourismApp.Repositories.AttractionRepository;
import com.spu.TourismApp.Shared.Dto.Attraction.CreateAttractionDto;
import com.spu.TourismApp.Shared.Dto.Attraction.AttractionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;

    @Autowired
    public AttractionServiceImpl(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    @Override
    public List<AttractionDto> getAllTouristAttractions() {
        List<Attraction> attractions = attractionRepository.findAll();

        List<AttractionDto> response = new ArrayList<>();

        for (Attraction attraction : attractions) {
            AttractionDto dto = new AttractionDto();

            dto = mapAttractionToDto(attraction);

            response.add(dto);
        }

        return response;
    }

    @Override
    public AttractionDto getTouristAttractionDetails(Integer id) {
        Attraction attraction = attractionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attraction with id: " + id + " not found"));

        AttractionDto response = new AttractionDto();

        response = mapAttractionToDto(attraction);

        return response;
    }

    @Override
    public Attraction getTouristAttraction(Integer id) {
        return attractionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist Attraction Not Found"));
    }

    @Override
    public void createTouristAttraction(CreateAttractionDto request) {
        Attraction attraction = new Attraction();

        attraction.setName(request.getName());
        attraction.setDescription(request.getDescription());
        attraction.setAddress(request.getAddress());
        attraction.setPhone(request.getPhone());
        attraction.setImageUrl(request.getImageUrl());

        attractionRepository.save(attraction);
    }

    @Override
    public void updateTouristAttraction(AttractionDto request) {
        Attraction attraction = attractionRepository.findById(request.getId())
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
        Attraction attraction = attractionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist Attraction Not Found"));

        attractionRepository.delete(attraction);
    }

    private static AttractionDto mapAttractionToDto(Attraction attraction) {
        AttractionDto dto = new AttractionDto();

        dto.setId(attraction.getId());
        dto.setName(attraction.getName());
        dto.setDescription(attraction.getDescription());
        dto.setAddress(attraction.getAddress());
        dto.setPhone(attraction.getPhone());
        dto.setImageUrl(attraction.getImageUrl());
        return dto;
    }

    private static Attraction mapDtoToAttraction(AttractionDto dto) {
        Attraction attraction = new Attraction();

        attraction.setId(dto.getId());
        attraction.setName(dto.getName());
        attraction.setDescription(dto.getDescription());
        attraction.setAddress(dto.getAddress());
        attraction.setPhone(dto.getPhone());
        attraction.setImageUrl(dto.getImageUrl());
        return attraction;
    }
}
