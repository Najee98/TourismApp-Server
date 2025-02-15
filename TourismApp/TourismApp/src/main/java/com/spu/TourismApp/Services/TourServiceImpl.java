package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.Agency;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Models.Tour;
import com.spu.TourismApp.Repositories.TourRepository;
import com.spu.TourismApp.Repositories.AgencyRepository;
import com.spu.TourismApp.Services.Utils.UtilsService;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import com.spu.TourismApp.Shared.Dto.Tour.CreateTourDto;
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final ReservationService reservationService;
    private final AgencyRepository agencyRepository;
    private final UserService userService;
    private final UtilsService utilsService;

//    @PersistenceContext
//    private EntityManager entityManager;


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

    @Transactional
    @Override
    public void createTour(CreateTourDto request) {
        Tour tour = new Tour();

        tour.setName(request.getTourName());
        tour.setMaxSubscribersCount(request.getMaxSubscribersCount());

        tour.setAgency(
                utilsService.getLoggedInUserAgency()
        );

//        Agency agency = utilsService.getLoggedInUserAgency();
//        Agency attachedAgency = entityManager.find(Agency.class, agency.getId());

       // tour.setAgency(attachedAgency);

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

    @Transactional
    @Override
    public void deleteTour(Integer id) {
        Optional<Tour> tourOptional = tourRepository.findById(id);

        if (tourOptional.isPresent()){
            Tour tour = tourOptional.get();

            Agency tourAgency = tour.getAgency();
            if (tourAgency != null) {
                tour.setAgency(null);
                tourRepository.save(tour);
            }
            List<Reservation> tourReservations = tour.getReservations();
            if (tourReservations != null) {
                for (Reservation reservation : tourReservations) {
                    reservation.setTour(null);
                    reservationService.saveReservation(reservation);
                }
                tour.setReservations(null);
                tourRepository.save(tour);
            }

            tourRepository.delete(tour);
        } else {
            throw new ResourceNotFoundException("Agency not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public void addUserToTour(Integer tourId) {
        Integer tourUserId = userService.getUserFromLogin().getId();

        AppUser tourUser = userService.getUserById(tourUserId);

        Optional<Tour> tourOptional = tourRepository.findById(tourId);

        if (tourOptional.isPresent() && tourUser != null) {
            Tour tour = tourOptional.get();

            tourUser.addTour(tour);
            userService.saveUser(tourUser);
        }
    }

    @Override
    @Transactional
    public void removeUserFromTour(Integer tourId) {
        AppUser tourUser = userService.getUserFromLogin();

        Optional<Tour> tourOptional = tourRepository.findById(tourId);

        if (tourOptional.isPresent() && tourUser != null) {
            Tour tour = tourOptional.get();

            tourUser.removeTour(tour);
            userService.saveUser(tourUser);
        }
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
