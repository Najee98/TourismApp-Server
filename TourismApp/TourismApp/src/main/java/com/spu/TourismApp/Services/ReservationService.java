package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Shared.Dto.Reservation.*;

import java.util.List;

public interface ReservationService {

    List<ReservationDetailsDto> getAllUserReservations();
    ReservationDetailsDto getReservationById(Integer id);
    void createReservation(ReservationDto request);

    void updateReservation(Integer reservationId, ReservationDto request);
    void deleteReservation(Integer reservationId);

    boolean isReservationValid(ReservationDto request);

    List<Reservation> findReservationsForTour(List<Integer> reservationIds);

    List<Reservation> getTourReservations(Integer tourId);
}
