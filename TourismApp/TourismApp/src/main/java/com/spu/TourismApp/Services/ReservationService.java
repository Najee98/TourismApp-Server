package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Shared.Dto.Reservation.CreateAttractionReservationDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDto;

import java.util.List;

public interface ReservationService {

    List<ReservationDto> getAllUserReservations();
    ReservationDto getReservationById(Integer id);
    Reservation createReservation(CreateAttractionReservationDto request);
//    Reservation createReservationByAgency(CreateReservationDto request);

    Reservation updateReservation(Integer reservationId, CreateAttractionReservationDto request);
    void deleteReservation(Integer reservationId);

    boolean isReservationValid(CreateAttractionReservationDto request);

    List<Reservation> findReservationsForTour(List<Integer> reservationIds);
}
