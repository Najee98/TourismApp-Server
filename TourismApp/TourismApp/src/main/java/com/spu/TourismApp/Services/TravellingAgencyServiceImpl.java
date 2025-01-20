package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Models.TravellingAgency;
import com.spu.TourismApp.Repositories.ReservationRepository;
import com.spu.TourismApp.Repositories.TourRepository;
import com.spu.TourismApp.Repositories.TravellingAgencyRepository;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDto;
import com.spu.TourismApp.Shared.Dto.TravellingAgency.AgencyTourDbResult;
import com.spu.TourismApp.Shared.Dto.TravellingAgency.AgencyTourDto;
import com.spu.TourismApp.Shared.Dto.TravellingAgency.TravellingAgencyDto;
import com.spu.TourismApp.Shared.Dto.TravellingAgency.CreateTravellingAgencyDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TravellingAgencyServiceImpl implements TravellingAgencyService {

    private final TravellingAgencyRepository agencyRepository;
    private final TourRepository tourRepository;
    private final ReservationRepository reservationRepository;

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

//    @Override
//    public List<AgencyTourDto> getAgencyTours(Integer agencyId) {
//
//        List<AgencyTourDto> response = tourRepository.getAgencyTours(agencyId);
//
//        List<AgencyTourDto> response = new ArrayList<>();
//        List<ReservationDto> reservationDtos = new ArrayList<>();
//
//        for (AgencyTourDbResult agencyTour : agencyTourDtos) {
//            AgencyTourDto dto = new AgencyTourDto();
//
//            dto.setTourId(agencyTour.getTourId());
//            dto.setAgencyId(agencyTour.getAgencyId());
//            dto.setAgencyName(agencyTour.getAgencyName());
//            dto.setTourName(agencyTour.getTourName());
//            dto.setStartDate(agencyTour.getStartDate());
//            dto.setEndDate(agencyTour.getEndDate());
//
//            reservationDtos = mapReservationToDto(agencyTour.getReservations());
//
//            dto.setReservations(reservationDtos);
//        }
//
//        return response;
//    }

    public List<AgencyTourDto> getAgencyTours(Integer agencyId) {
        List<AgencyTourDto> tours = tourRepository.getAgencyTours(agencyId);

        for (AgencyTourDto tour : tours) {
            List<Reservation> reservations = fetchReservationsByTourId(tour.getTourId());
            List<ReservationDto> reservationDtos = reservations.stream()
                    .map(this::mapReservationToDto)
                    .collect(Collectors.toList());
            tour.setReservations(reservationDtos);
        }

        return tours;
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

//    private List<ReservationDto> mapReservationToDto(List<Reservation> reservations) {
//
//        List<ReservationDto> reservationDtos = new ArrayList<>();
//
//        for (Reservation reservation : reservations) {
//            ReservationDto dto = new ReservationDto();
//
//            dto.setReservationId(reservation.getId());
//            dto.setReservationUserName(reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName());
//            dto.setReservationType(reservation.getReservationType());
//
//            dto.setAttractionId(reservation.getAttraction().getId());
//            dto.setAttractionName(reservation.getAttraction().getName());
//
//            dto.setHotelId(reservation.getHotel().getId());
//            dto.setHotelName(reservation.getHotel().getName());
//
//            dto.setRestaurantId(reservation.getRestaurant().getId());
//            dto.setRestaurantName(reservation.getRestaurant().getName());
//
//            reservationDtos.add(dto);
//        }
//
//        return reservationDtos;
//    }

    private List<Reservation> fetchReservationsByTourId(Integer tourId) {
        return reservationRepository.findAllByTourId(tourId);
    }

    private ReservationDto mapReservationToDto(Reservation reservation) {
        // Map Reservation entity to ReservationDto
        return new ReservationDto(
                reservation.getId(),
                reservation.getUser() != null
                        ? reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName()
                        : null,
                reservation.getReservationType(),
                reservation.getAttraction() != null ? reservation.getAttraction().getId() : null,
                reservation.getAttraction() != null ? reservation.getAttraction().getName() : null,
                reservation.getRestaurant() != null ? reservation.getRestaurant().getId() : null,
                reservation.getRestaurant() != null ? reservation.getRestaurant().getName() : null,
                reservation.getHotel() != null ? reservation.getHotel().getId() : null,
                reservation.getHotel() != null ? reservation.getHotel().getName() : null
        );
    }
}
