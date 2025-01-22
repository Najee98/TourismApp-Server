package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.DuplicatedResourceException;
import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.*;
import com.spu.TourismApp.Models.Utils.ReservationDetail;
import com.spu.TourismApp.Models.Utils.ReservationType;
import com.spu.TourismApp.Repositories.AgencyRepository;
import com.spu.TourismApp.Repositories.ReservationRepository;
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
    private final UserService userService;
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

//            reservationDto.setReservationId(reservation.getId());
//            reservationDto.setReservationUserName(
//                    reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName()
//            );
            reservationDto.setReservationType(reservation.getReservationType());

            if (reservation.getHotel() != null) {
                reservationDto.setHotelId(reservation.getHotel().getId());
                reservationDto.setHotelName(reservation.getHotel().getName());
            }

            if (reservation.getRestaurant() != null) {
                reservationDto.setRestaurantId(reservation.getRestaurant().getId());
                reservationDto.setRestaurantName(reservation.getRestaurant().getName());
            }

            if (reservation.getAttraction() != null) {
                reservationDto.setAttractionId(reservation.getAttraction().getId());
                reservationDto.setAttractionName(reservation.getAttraction().getName());
            }

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

        if (reservation.getHotel() != null) {
            response.setHotelId(reservation.getHotel().getId());
            response.setHotelName(reservation.getHotel().getName());
        }

        if (reservation.getRestaurant() != null) {
            response.setRestaurantId(reservation.getRestaurant().getId());
            response.setRestaurantName(reservation.getRestaurant().getName());
        }

        if (reservation.getAttraction() != null) {
            response.setAttractionId(reservation.getAttraction().getId());
            response.setAttractionName(reservation.getAttraction().getName());
        }

        return response;
    }

    @Transactional
    @Override
    public Reservation createReservation(ReservationDto request) {

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

            reservation.setAgency(agencyRepository.findById(request.getAgencyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Agency with id: " + request.getAgencyId() + " not found")));

            return reservationRepository.save(reservation);

        }else{
            throw new DuplicatedResourceException("Reservation already exists within this period of time.");
        }

    }


//    @Override
//    public Reservation createReservationByAgency(CreateReservationDto request) {
//        Reservation reservation = new Reservation();
//        ReservationDetail details = new ReservationDetail(
//                request.getHotelRoomNumber(),
//                request.getRestaurantTableNumber()
//        );
//
//        //If agency id is present -> true : else -> false
//        reservation.setAgencyReservation(request.getAgencyId() != null);
//
//        AppUser user = userService.getUserById(request.getReservationUserId());
//        reservation.setUser(user);
//
//        reservation.setReservationType(request.getReservationType());
//
//
//        switch (request.getReservationType()) {
//            case HotelReservation -> reservation.setHotel(
//                    hotelService.getHotel(request.getHotelId())
//            );
//            case RestaurantReservation -> reservation.setRestaurant(
//                    restaurantService.getRestaurant(request.getRestaurantId())
//            );
//            case ATTRACTION_Reservation -> reservation.setAttraction(
//                    attractionService.getTouristAttraction(request.getAttractionId())
//            );
//        }
//
//        reservation.setReservationDetail(details);
//
//        reservation.setFromDate(request.getFromDate());
//        reservation.setToDate(request.getToDate());
//
//        return reservationRepository.save(reservation);
//    }

    @Transactional
    @Override
    public Reservation updateReservation(Integer reservationId, ReservationDto request) {
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
        reservation.setAgency(agencyRepository.findById(request.getAgencyId())
                .orElseThrow(() -> new ResourceNotFoundException("Agency with id: " + request.getAgencyId() + " not found")));

        reservation.setFromDate(request.getFromDate());
        reservation.setToDate(request.getToDate());

        return reservationRepository.save(reservation);
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

}
