package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Models.Agency;
import com.spu.TourismApp.Repositories.ReservationRepository;
import com.spu.TourismApp.Repositories.TourRepository;
import com.spu.TourismApp.Repositories.AgencyRepository;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyTourDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyDto;
import com.spu.TourismApp.Shared.Dto.Agency.CreateAgencyDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final TourRepository tourRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public List<AgencyDto> getAllTravellingAgencies() {
        return agencyRepository.findAllAgencies();
    }

    @Override
    public AgencyDto getTravellingAgency(Integer id) {
        Agency agency = agencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + id));

        AgencyDto response = new AgencyDto(
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
            List<ReservationDetailsDto> reservationDtos = reservations.stream()
                    .map(this::mapReservationToDto)
                    .collect(Collectors.toList());
            tour.setReservations(reservationDtos);
        }

        return tours;
    }

    @Override
    public void createTravellingAgency(CreateAgencyDto request) {
        Agency agency = toEntity(request);
        agencyRepository.save(agency);
    }

    @Override
    public void updateTravellingAgency(AgencyDto request) {
        Agency existingAgency = agencyRepository.findById(request.getId())
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

    private CreateAgencyDto toDto(Agency agency) {
        return new CreateAgencyDto(
                agency.getName(),
                agency.getAddress(),
                agency.getPhone(),
                agency.getImageUrl()
        );
    }

    private Agency toEntity(CreateAgencyDto dto) {
        return new Agency(
                null, // ID is auto-generated
                dto.getName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getImageUrl()
//                null
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

    private ReservationDetailsDto mapReservationToDto(Reservation reservation) {
        // Map Reservation entity to ReservationDto

        Integer relatedId = -1;
        String relatedName = " ";

        if (reservation.getHotel() != null) {
            relatedId = reservation.getHotel().getId();
            relatedName = reservation.getHotel().getName();
        } else if (reservation.getRestaurant() != null){
            relatedId = reservation.getRestaurant().getId();
            relatedName = reservation.getRestaurant().getName();
        } else if (reservation.getAttraction() != null){
            relatedId = reservation.getAttraction().getId();
            relatedName = reservation.getAttraction().getName();
        }

        ReservationDetailsDto dto = new ReservationDetailsDto();
        return new ReservationDetailsDto(
                reservation.getId(),
                reservation.getAgency().getId(),
                reservation.getReservationType(),
                relatedId,
                relatedName,
                reservation.getFromDate(),
                reservation.getToDate()
        );
    }
}
