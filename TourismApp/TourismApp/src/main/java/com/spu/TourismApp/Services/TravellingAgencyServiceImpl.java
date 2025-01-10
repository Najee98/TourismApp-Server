package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.TravellingAgency;
import com.spu.TourismApp.Repositories.TravellingAgencyRepository;
import com.spu.TourismApp.Shared.Dto.TouristAttractionDto;
import com.spu.TourismApp.Shared.Dto.TravellingAgencyDto;
import com.spu.TourismApp.Shared.Dto.CreateTravellingAgencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellingAgencyServiceImpl implements TravellingAgencyService {

    private final TravellingAgencyRepository agencyRepository;

    @Autowired
    public TravellingAgencyServiceImpl(TravellingAgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public List<TravellingAgencyDto> getAllTravellingAgencies() {
        return agencyRepository.findAllAgencies();
    }

    @Override
    public TravellingAgencyDto getTravellingAgency(Integer id) {
        TravellingAgency agency = agencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + id));

        TravellingAgencyDto response = new TravellingAgencyDto(
                agency.getId(),
                agency.getName(),
                agency.getAddress(),
                agency.getPhone(),
                agency.getImageUrl()
        );

        return response;
    }

    @Override
    public void createTravellingAgency(CreateTravellingAgencyDto request) {
        TravellingAgency agency = toEntity(request);
        agencyRepository.save(agency);
    }

    @Override
    public void updateTravellingAgency(TravellingAgencyDto request) {
        TravellingAgency existingAgency = agencyRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + request.getId()));
        
        existingAgency.setName(request.getName());
        existingAgency.setAddress(request.getAddress());
        existingAgency.setPhone(request.getPhone());
        existingAgency.setImageUrl(request.getImageUrl());
        
        agencyRepository.save(existingAgency);
    }

    @Override
    public void deleteTravellingAgency(Integer id) {
        if (!agencyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agency not found with id: " + id);
        }
        agencyRepository.deleteById(id);
    }


    private CreateTravellingAgencyDto toDto(TravellingAgency agency) {
        return new CreateTravellingAgencyDto(
                agency.getName(),
                agency.getAddress(),
                agency.getPhone(),
                agency.getImageUrl()
        );
    }

    private TravellingAgency toEntity(CreateTravellingAgencyDto dto) {
        return new TravellingAgency(
                null, // ID is auto-generated
                dto.getName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getImageUrl(),
                null // Agency users will not be set here
        );
    }
}
