package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Tour;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDto;
import com.spu.TourismApp.Shared.Dto.Tour.CreateTourDto;
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;

import java.util.List;

public interface TourService {

    List<TourDto> getAllTours();

    TourDto getTourById(Integer id);

    void createTour(CreateTourDto createTourDto);

    void updateTour(Integer id, CreateTourDto request);

    void deleteTour(Integer id);

    void addUserToTour(Integer tourId);

    List<TourDto> getAllUserTours();

    void removeUserFromTour(Integer tourId);

    List<ReservationDetailsDto> getTourReservations(Integer tourId);
}
