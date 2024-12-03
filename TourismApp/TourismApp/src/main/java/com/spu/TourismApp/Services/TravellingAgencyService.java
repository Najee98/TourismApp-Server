package com.spu.TourismApp.Services;

import com.spu.TourismApp.Shared.Dto.TravellingAgencyDto;
import com.spu.TourismApp.Shared.Dto.CreateTravellingAgencyDto;

import java.util.List;

public interface TravellingAgencyService {

    List<TravellingAgencyDto> getAllTravellingAgencies();

    TravellingAgencyDto getTravellingAgency(Integer id);

    void createTravellingAgency(CreateTravellingAgencyDto request);

    void updateTravellingAgency(TravellingAgencyDto request);

    void deleteTravellingAgency(Integer id);
}
