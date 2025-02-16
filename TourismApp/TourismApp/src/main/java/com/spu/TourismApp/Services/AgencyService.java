package com.spu.TourismApp.Services;

import com.spu.TourismApp.Shared.Dto.Agency.AgencyTourDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyDto;
import com.spu.TourismApp.Shared.Dto.Agency.CreateAgencyDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;

import java.util.List;

public interface AgencyService {

    List<AgencyDto> getAllAgencies();

    AgencyDto getAgency(Integer id);

    void createAgency(CreateAgencyDto request);

    void updateAgency(AgencyDto request);

    void deleteAgency(Integer id);

    List<TourDto> getAgencyTours();

    List<ReservationDetailsDto> getAgencyReservations();
}
