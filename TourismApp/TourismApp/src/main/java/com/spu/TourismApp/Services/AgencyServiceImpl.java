package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Models.Agency;
import com.spu.TourismApp.Models.Utils.Role;
import com.spu.TourismApp.Repositories.AppUserRepository;
import com.spu.TourismApp.Repositories.ReservationRepository;
import com.spu.TourismApp.Repositories.TourRepository;
import com.spu.TourismApp.Repositories.AgencyRepository;
import com.spu.TourismApp.Services.Utils.UtilsService;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyTourDto;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyDto;
import com.spu.TourismApp.Shared.Dto.Agency.CreateAgencyDto;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final TourRepository tourRepository;
    private final ReservationRepository reservationRepository;
    private final AppUserRepository userRepository;
    private final UtilsService utilsService;

    @Override
    public List<AgencyDto> getAllAgencies() {
        return agencyRepository.findAllAgencies();
    }

    @Override
    public AgencyDto getAgency(Integer id) {
        Agency agency = agencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + id));

        AgencyDto response = new AgencyDto(
                agency.getId(),
                agency.getName(),
                agency.getAddress(),
                agency.getPhone(),
                agency.getImageUrl(),
                agency.getManager().getId()
        );

        return response;
    }

    public List<AgencyTourDto> getAgencyTours() {
        List<AgencyTourDto> tours = tourRepository.getAgencyTours(
                utilsService.getLoggedInUserAgency().getId()
        );

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
    public List<ReservationDetailsDto> getAgencyReservations() {
        Agency requetedAgency = utilsService.getLoggedInUserAgency();


        List<Reservation> reservations = reservationRepository.getAgencyReservations(requetedAgency.getId());

        List<ReservationDetailsDto> response = new ArrayList<>();



        for(Reservation reservation : reservations) {
            ReservationDetailsDto reservationDto = new ReservationDetailsDto();

            reservationDto.setReservationId(reservation.getId());
            reservationDto.setReservationType(reservation.getReservationType());
            reservationDto.setAgencyId(
                    agencyRepository.findById(reservation.getAgency().getId()).orElse(null).getId()
            );

            reservationDto.setReservationType(reservation.getReservationType());

            if (reservation.getHotel() != null) {
                reservationDto.setRelatedId(reservation.getHotel().getId());
                reservationDto.setRelatedName(reservation.getHotel().getName());
            }

            if (reservation.getRestaurant() != null) {
                reservationDto.setRelatedId(reservation.getRestaurant().getId());
                reservationDto.setRelatedName(reservation.getRestaurant().getName());
            }

            if (reservation.getAttraction() != null) {
                reservationDto.setRelatedId(reservation.getAttraction().getId());
                reservationDto.setRelatedName(reservation.getAttraction().getName());
            }

            reservationDto.setFromDate(reservation.getFromDate());
            reservationDto.setToDate(reservation.getToDate());

            response.add(reservationDto);
        }

        return response;
    }

    @Override
    public void createAgency(CreateAgencyDto request) {
        Agency agency = toEntity(request);

        agency.setManager(
                userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + request.getManagerId())));

        agency.getManager().setAgency(agency);

        agencyRepository.save(agency);
    }

    @Override
    public void updateAgency(AgencyDto request) {
        Agency existingAgency = agencyRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + request.getId()));
        
        existingAgency.setName(request.getName());
        existingAgency.setAddress(request.getAddress());
        existingAgency.setPhone(request.getPhone());
        existingAgency.setImageUrl(request.getImageUrl());
        existingAgency.setManager(
                userRepository.findById(request.getManagerId())
                        .orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + request.getManagerId()))
        );
        
        agencyRepository.save(existingAgency);
    }

    @Override
    public void deleteAgency(Integer id) {
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
                agency.getImageUrl(),
                userRepository.findById(agency.getId()).get().getId()
        );
    }

    private Agency toEntity(CreateAgencyDto dto) {
        return new Agency(
                null, // ID is auto-generated
                dto.getName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getImageUrl(),
                userRepository.findById(dto.getManagerId()).get()
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
