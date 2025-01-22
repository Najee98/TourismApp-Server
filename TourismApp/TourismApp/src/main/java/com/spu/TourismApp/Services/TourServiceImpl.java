package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Models.Tour;
import com.spu.TourismApp.Repositories.TourRepository;
import com.spu.TourismApp.Repositories.AgencyRepository;
import com.spu.TourismApp.Shared.Dto.Tour.CreateTourDto;
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final ReservationService reservationService;
    private final AgencyRepository agencyRepository;
    private final UserService userService;

    @Override
    public List<TourDto> getAllTours() {
        List<Tour> tours = tourRepository.findAll();
        List<TourDto> response = new ArrayList<>();

        for (Tour tour : tours) {
            TourDto dto = new TourDto();

            dto = convertToDto(tour);

            response.add(dto);
        }

        return response;
    }

    @Override
    public TourDto getTourById(Integer id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found"));
        return convertToDto(tour);
    }

    @Override
    @Transactional
    public Tour createTour(CreateTourDto request) {
        Tour tour = new Tour();

        tour.setName(request.getTourName());

        tour.setAgency(
                agencyRepository.findById(request.getAgencyId()).get()
        );

        tour.setStartDate(request.getStartDate());
        tour.setEndDate(request.getEndDate());

        List<Reservation> reservations = reservationService.findReservationsForTour(request.getReservationIds());
        tour.setReservations(reservations);

        // to satisfy the relationship between the reservations and the tour
        reservations.forEach(reservation -> reservation.setTour(tour));

        return tourRepository.save(tour);
    }

    @Override
    public Tour updateTour(Integer id, CreateTourDto request) {
        Tour tour;
        tour = tourRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tour not found"));

        tour.setName(request.getTourName());
        tour.setStartDate(request.getStartDate());
        tour.setEndDate(request.getEndDate());

        List<Reservation> reservations = reservationService.findReservationsForTour(request.getReservationIds());
        tour.setReservations(reservations);

        reservations.forEach(reservation -> reservation.setTour(tour));

        return tourRepository.save(tour);
    }

    @Override
    public void bookUserForTour(Integer tourId, List<Integer> userIds) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found"));

        for (Integer userId : userIds) {
            AppUser user = userService.getUserById(userId);

            tour.getSubscribers().add(user);
        }

    }

    @Override
    public void deleteTour(Integer id) {
        tourRepository.deleteById(id);
    }

    private TourDto convertToDto(Tour tour) {
        return new TourDto(
            tour.getId(),
            tour.getName(),
            tour.getAgency().getId(),
            tour.getReservations().stream().map(Reservation::getId).collect(Collectors.toList()),
            tour.getStartDate(),
            tour.getEndDate()
        );
    }
}
