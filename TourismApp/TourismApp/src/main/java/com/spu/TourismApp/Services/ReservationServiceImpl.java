package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.DuplicatedResourceException;
import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.*;
import com.spu.TourismApp.Models.Utils.ReservationDetail;
import com.spu.TourismApp.Models.Utils.ReservationType;
import com.spu.TourismApp.Repositories.AgencyRepository;
import com.spu.TourismApp.Repositories.ReservationRepository;
import com.spu.TourismApp.Services.Utils.UtilsService;
import com.spu.TourismApp.Shared.Dto.Reservation.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UtilsService utilsService;
    private final HotelService hotelService;
    private final AttractionService attractionService;
    private final RestaurantService restaurantService;
    private final AgencyRepository agencyRepository;

    @Override
    public List<ReservationDetailsDto> getAllUserReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<ReservationDetailsDto> response = new ArrayList<>();

        for(Reservation reservation : reservations) {
            ReservationDetailsDto reservationDto = new ReservationDetailsDto();

            reservationDto.setReservationId(reservation.getId());
            reservationDto.setReservationType(reservation.getReservationType());
            reservation.setAgency(
                    agencyRepository.findById(reservation.getAgency().getId()).orElse(null)
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
    public ReservationDetailsDto getReservationById(Integer id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with id: " + id + " not found"));

        ReservationDetailsDto response = new ReservationDetailsDto();

        response.setReservationId(reservation.getId());
//        response.setReservationUserName(reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName());
        response.setReservationType(reservation.getReservationType());

        response.setAgencyId(reservation.getAgency().getId());

        if (reservation.getHotel() != null) {
            response.setRelatedId(reservation.getHotel().getId());
            response.setRelatedName(reservation.getHotel().getName());
        }

        if (reservation.getRestaurant() != null) {
            response.setRelatedId(reservation.getRestaurant().getId());
            response.setRelatedName(reservation.getRestaurant().getName());
        }

        if (reservation.getAttraction() != null) {
            response.setRelatedId(reservation.getAttraction().getId());
            response.setRelatedName(reservation.getAttraction().getName());
        }

        response.setFromDate(reservation.getFromDate());
        response.setToDate(reservation.getToDate());

        return response;
    }

    @Transactional
    @Override
    public void createReservation(ReservationDto request) {

        Reservation reservation = new Reservation();

        boolean validReservation = isReservationValid(request);

        if (validReservation) {
            ReservationDetail details = new ReservationDetail();

            reservation.setReservationType(ReservationType.valueOf(request.getReservationType()));

            switch (ReservationType.valueOf(request.getReservationType())) {
                case HOTEL_RESERVATION -> {
                    reservation.setHotel(
                            hotelService.getHotel(request.getReservationRelatedId())
                    );
                    details.setRoomNumber(request.getTableOrRoomNumber());
                }
                case RESTAURANT_RESERVATION -> {
                    reservation.setRestaurant(
                            restaurantService.getRestaurant(request.getReservationRelatedId())
                    );
                    details.setTableNumber(request.getTableOrRoomNumber());
                }
                case ATTRACTION_RESERVATION -> {
                    reservation.setAttraction(
                            attractionService.getTouristAttraction(request.getReservationRelatedId())
                    );
                }
            }

            reservation.setReservationDetail(details);

            reservation.setFromDate(request.getFromDate());
            reservation.setToDate(request.getToDate());

            Integer agencyId = utilsService.getLoggedInUserAgency().getId();

            reservation.setAgency(agencyRepository.findById(
                    agencyId
                    )
                    .orElseThrow(() -> new ResourceNotFoundException("Agency with id: " + agencyId + " not found")));

            reservationRepository.save(reservation);

        }else{
            throw new DuplicatedResourceException("Reservation already exists within this period of time.");
        }

    }

    @Transactional
    @Override
    public void updateReservation(Integer reservationId, ReservationDto request) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with id: " + reservationId + " not found."));

        ReservationDetail details = new ReservationDetail();

        reservation.setReservationType(ReservationType.valueOf(request.getReservationType()));

        switch (ReservationType.valueOf(request.getReservationType())) {
            case HOTEL_RESERVATION -> {
                reservation.setHotel(
                        hotelService.getHotel(request.getReservationRelatedId())
                );
                details.setRoomNumber(request.getTableOrRoomNumber());
            }
            case RESTAURANT_RESERVATION -> {
                reservation.setRestaurant(
                        restaurantService.getRestaurant(request.getReservationRelatedId())
                );
                details.setTableNumber(request.getTableOrRoomNumber());
            }
            case ATTRACTION_RESERVATION -> {
                reservation.setAttraction(
                        attractionService.getTouristAttraction(request.getReservationRelatedId())
                );
            }
        }

        reservation.setReservationDetail(details);
//        reservation.setAgency(agencyRepository.findById(request.getAgencyId())
//                .orElseThrow(() -> new ResourceNotFoundException("Agency with id: " + request.getAgencyId() + " not found")));

        reservation.setFromDate(request.getFromDate());
        reservation.setToDate(request.getToDate());

        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation: " + reservationId + " doesn't exist."));

        reservationRepository.delete(reservation);
    }

    @Override
    public boolean isReservationValid(ReservationDto request){

        List<Reservation> intersectedReservations = new ArrayList<>();

        intersectedReservations = reservationRepository
                .fetchIntersectedReservations(request.getFromDate(), request.getToDate());

        if (intersectedReservations.isEmpty()){
            return true;
        }

        for (Reservation reservation : intersectedReservations){
            if ((reservation.getReservationDetail().getRoomNumber() == 0
                && reservation.getReservationDetail().getTableNumber() == request.getTableOrRoomNumber())
            || (reservation.getReservationDetail().getTableNumber() == 0
                && reservation.getReservationDetail().getRoomNumber() == request.getTableOrRoomNumber())){
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Reservation> findReservationsForTour(List<Integer> reservationIds) {
        List<Reservation> response = new ArrayList<>();

        for (Integer reservationId : reservationIds){
            response.add(reservationRepository.findById(reservationId)
                    .orElseThrow(() -> new ResourceNotFoundException("Reservation: " + reservationId + " doesn't exist.")));
        }

        return response;
    }

    @Override
    public List<Reservation> getTourReservations(Integer tourId) {
        return reservationRepository.getTourReservations(tourId);
    }

}
