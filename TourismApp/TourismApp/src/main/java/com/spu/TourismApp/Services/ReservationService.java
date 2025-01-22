package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Shared.Dto.Reservation.*;

import java.util.List;

public interface ReservationService {

    List<ReservationDetailsDto> getAllUserReservations();
    ReservationDetailsDto getReservationById(Integer id);
    Reservation createReservation(ReservationDto request);

    Reservation updateReservation(Integer reservationId, ReservationDto request);
    void deleteReservation(Integer reservationId);

    boolean isReservationValid(ReservationDto request);

    List<Reservation> findReservationsForTour(List<Integer> reservationIds);

}
