package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Models.Tour;
import com.spu.TourismApp.Repositories.TourRepository;
import com.spu.TourismApp.Repositories.AgencyRepository;
import com.spu.TourismApp.Services.Utils.UtilsService;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Tour.CreateTourDto;
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final ReservationService reservationService;
    private final AgencyRepository agencyRepository;
    private final UserService userService;
    private final UtilsService utilsService;

    @Override
    public List<TourDto> getAllTours() {
        List<Tour> tours = tourRepository.findAll();
        List<TourDto> response = new ArrayList<>();

        for (Tour tour : tours) {
            TourDto dto = new TourDto();

            dto = utilsService.convertTourToDto(tour);

            response.add(dto);
        }
        return response;
    }

    @Override
    public TourDto getTourById(Integer id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found"));
        return utilsService.convertTourToDto(tour);
    }

    @Override
    public void createTour(CreateTourDto request) {
        Tour tour = new Tour();

        tour.setName(request.getTourName());
        tour.setMaxSubscribersCount(request.getMaxSubscribersCount());

        tour.setAgency(
                utilsService.getLoggedInUserAgency()
        );

        tour.setStartDate(request.getStartDate());
        tour.setEndDate(request.getEndDate());

        List<Reservation> reservations = reservationService.findReservationsForTour(request.getReservationIds());
        tour.setReservations(reservations);

        // to satisfy the relationship between the reservations and the tour
        reservations.forEach(reservation -> reservation.setTour(tour));

        tourRepository.save(tour);
    }

    @Override
    public void updateTour(Integer id, CreateTourDto request) {
        Tour tour;
        tour = tourRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tour not found"));

        tour.setMaxSubscribersCount(request.getMaxSubscribersCount());
        tour.setName(request.getTourName());
        tour.setStartDate(request.getStartDate());
        tour.setEndDate(request.getEndDate());

        List<Reservation> reservations = reservationService.findReservationsForTour(request.getReservationIds());
        tour.setReservations(reservations);

        reservations.forEach(reservation -> reservation.setTour(tour));

        tourRepository.save(tour);
    }

    @Override
    public void deleteTour(Integer id) {
        tourRepository.deleteById(id);
    }

    @Override
    public void addUserToTour(Integer tourId) {
        Integer tourUserId = userService.getUserFromLogin().getId();

        AppUser tourUser = userService.getUserById(tourUserId);
        Tour tour = tourRepository.findById(tourId).get();

        tourUser.tours.add(tour);
        tour.subscribers.add(tourUser);

        userService.saveUser(tourUser);
        tourRepository.save(tour);
    }

    @Override
    public void removeUserFromTour(Integer tourId) {
        AppUser tourUser = userService.getUserFromLogin();

        Optional<Tour> tour = Optional.ofNullable(
                tourRepository.findById(tourId)
                        .orElseThrow(() -> new ResourceNotFoundException("Tour not found"))
        );

        userService.saveUser(tourUser);
        tourUser.getTours().remove(tour.get());
    }

    @Override
    public List<ReservationDetailsDto> getTourReservations(Integer tourId) {
        List<Reservation> reservations = reservationService.getTourReservations(tourId);

        List<ReservationDetailsDto> response = utilsService.convertToDetailsDto(reservations);

        return response;
    }

    @Override
    public List<TourDto> getAllUserTours() {
        AppUser user = userService.getUserFromLogin();

        List<TourDto> response = new ArrayList<>();

        List<Tour> tours = tourRepository.findAllByUser(user.getId());

        for (Tour tour : tours) {
            TourDto dto = new TourDto();
            dto = utilsService.convertTourToDto(tour);
            response.add(dto);
        }

        return response;
    }

}
