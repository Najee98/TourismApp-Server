package com.spu.TourismApp.Services;

import com.spu.TourismApp.Shared.Dto.Agency.AgencyTourDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyDto;
import com.spu.TourismApp.Shared.Dto.Agency.CreateAgencyDto;

import java.util.List;

public interface AgencyService {

    List<AgencyDto> getAllTravellingAgencies();

    AgencyDto getTravellingAgency(Integer id);

    void createTravellingAgency(CreateAgencyDto request);

    void updateTravellingAgency(AgencyDto request);

    void deleteTravellingAgency(Integer id);

    List<AgencyTourDto> getAgencyTours(Integer agencyId);
}
