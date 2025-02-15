package com.spu.TourismApp.Services.Utils;

import com.spu.TourismApp.Models.*;
import com.spu.TourismApp.Services.UserService;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyDto;
import com.spu.TourismApp.Shared.Dto.Agency.CreateAgencyDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilsService {

    private final UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    public Agency getLoggedInUserAgency() {
        AppUser agencyManager = userService.getUserFromLogin();
        if (agencyManager == null || agencyManager.getAgency() == null) {
            throw new IllegalStateException("User is not assigned to any agency");
        }

        return entityManager.merge(agencyManager.getAgency());
//        return agencyManager.getAgency();
    }

    public Hotel getLoggedInUserHotel() {
        AppUser hotelManager = userService.getUserFromLogin();
        return hotelManager.getHotel();
    }

    public Restaurant getLoggedInUserRestaurant() {
        AppUser agencyManager = userService.getUserFromLogin();
        return agencyManager.getRestaurant();
    }

    public TourDto convertTourToDto(Tour tour) {

        return new TourDto(
                tour.getId(),
                tour.getName(),
                mapAgencyToDto(tour.getAgency()),
                convertToDetailsDto(tour.getReservations()),
                tour.getStartDate(),
                tour.getEndDate()
        );
    }

    public List<ReservationDetailsDto> convertToDetailsDto (List<Reservation> reservations) {

        List<ReservationDetailsDto> reservationDetailsList = new ArrayList<>();

        for(Reservation reservation : reservations) {
            ReservationDetailsDto dto = new ReservationDetailsDto();

            dto.setReservationId(reservation.getId());
            dto.setReservationType(reservation.getReservationType());
            dto.setAgencyId(reservation.getAgency().getId());

            if (reservation.getHotel() != null) {
                dto.setRelatedId(reservation.getHotel().getId());
                dto.setRelatedName(reservation.getHotel().getName());
            }

            if (reservation.getRestaurant() != null) {
                dto.setRelatedId(reservation.getRestaurant().getId());
                dto.setRelatedName(reservation.getRestaurant().getName());
            }

            if (reservation.getAttraction() != null) {
                dto.setRelatedId(reservation.getAttraction().getId());
                dto.setRelatedName(reservation.getAttraction().getName());
            }

            dto.setFromDate(reservation.getFromDate());
            dto.setToDate(reservation.getToDate());

            reservationDetailsList.add(dto);

        }

        return reservationDetailsList;
    }

    public AgencyDto mapAgencyToDto(Agency agency) {
        return new AgencyDto(
                agency.getId(),
                agency.getName(),
                agency.getAddress(),
                agency.getPhone(),
                agency.getImageUrl(),
                agency.getManager().getId()
        );
    }

}
