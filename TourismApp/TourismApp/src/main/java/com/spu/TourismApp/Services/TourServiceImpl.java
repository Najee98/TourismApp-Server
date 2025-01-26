package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Models.Tour;
import com.spu.TourismApp.Repositories.TourRepository;
import com.spu.TourismApp.Repositories.AgencyRepository;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDto;
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
    public void createTour(CreateTourDto request) {
        Tour tour = new Tour();

        tour.setName(request.getTourName());
        tour.setMaxSubscribersCount(request.getMaxSubscribersCount());

        tour.setAgency(
                agencyRepository.findById(request.getAgencyId()).get()
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

//    @Override
//    public void bookUserForTour(Integer tourId, List<Integer> userIds) {
//        Tour tour = tourRepository.findById(tourId)
//                .orElseThrow(() -> new ResourceNotFoundException("Tour not found"));
//
//        for (Integer userId : userIds) {
//            AppUser user = userService.getUserById(userId);
//
//            tour.getSubscribers().add(user);
//        }
//    }

    @Override
    public void deleteTour(Integer id) {
        tourRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void addUserToTour(Integer tourId) {
        AppUser tourUser = userService.getUserFromLogin();

        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found"));

        tour.getSubscribers().add(tourUser);
        tourUser.getTours().add(tour);

        userService.saveUser(tourUser);
        tourRepository.save(tour);
    }

    @Transactional
    @Override
    public void removeUserFromTour(Integer tourId) {
        AppUser tourUser = userService.getUserFromLogin();

        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found"));

        tour.getSubscribers().remove(tourUser);
        tourUser.getTours().remove(tour);

        userService.saveUser(tourUser);
        tourRepository.save(tour);
    }

    @Override
    public List<ReservationDetailsDto> getTourReservations(Integer tourId) {
        List<Reservation> reservations = reservationService.getTourReservations(tourId);

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
    public List<TourDto> getAllUserTours() {
        AppUser user = userService.getUserFromLogin();

        List<TourDto> response = new ArrayList<>();

        List<Tour> tours = tourRepository.findAllByUser(user.getId());

        for (Tour tour : tours) {
            TourDto dto = new TourDto();
            dto = convertToDto(tour);
            response.add(dto);
        }

        return response;
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
