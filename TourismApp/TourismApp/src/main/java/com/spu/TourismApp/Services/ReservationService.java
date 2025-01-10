package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Shared.Dto.CreateReservationDto;
import com.spu.TourismApp.Shared.Dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    List<ReservationDto> getAllUserReservations();
    ReservationDto getReservationById(Integer id);
    Reservation createReservationByUser(CreateReservationDto request);
    Reservation createReservationByAgency(CreateReservationDto request);

    Reservation updateReservation(Integer reservationId, CreateReservationDto request);
    void deleteReservation(Integer reservationId);
}
