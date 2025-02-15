package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Models.Agency;
import com.spu.TourismApp.Models.Tour;
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
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<TourDto> getAgencyTours() {

        List<Tour> tours = tourRepository.findAllToursByAgency(
                utilsService.getLoggedInUserAgency().getId()
        );

        List<TourDto> response = new ArrayList<>();

        for (Tour tour : tours) {
            TourDto dto = new TourDto();

            dto = utilsService.convertTourToDto(tour);

            response.add(dto);
        }
        return response;
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

    @Transactional
    @Override
    public void deleteAgency(Integer id) {
        Optional<Agency> agencyOptional = agencyRepository.findById(id);

        if (agencyOptional.isPresent()){
            Agency agency = agencyOptional.get();

            // Unlink the manager from the agency
            AppUser manager = agency.getManager();
            if (manager != null) {
                manager.setAgency(null);
                userRepository.save(manager);
            }

            // Now delete the agency
            agencyRepository.delete(agency);
        } else {
            throw new ResourceNotFoundException("Agency not found with id: " + id);
        }
    }



    private Agency toEntity(CreateAgencyDto dto) {
        return new Agency(
                null, // ID is auto-generated
                dto.getName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getImageUrl(),
                userRepository.findById(dto.getManagerId()).get(),
                null
        );
    }

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
