package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.*;
import com.spu.TourismApp.Models.Utils.ReservationDetail;
import com.spu.TourismApp.Repositories.ReservationRepository;
import com.spu.TourismApp.Shared.Dto.CreateReservationDto;
import com.spu.TourismApp.Shared.Dto.ReservationDto;
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
    private final TouristAttractionService attractionService;
    private final RestaurantService restaurantService;

    @Override
    public List<ReservationDto> getAllUserReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<ReservationDto> response = new ArrayList<>();

        for(Reservation reservation : reservations) {
            ReservationDto reservationDto = new ReservationDto();

            reservationDto.setReservationId(reservation.getId());
            reservationDto.setReservationUserName(reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName());
            reservationDto.setReservationType(reservation.getReservationType());

            reservationDto.setHotelId(reservation.getHotel().getId());
            reservationDto.setHotelName(reservation.getHotel().getName());

            reservationDto.setRestaurantId(reservation.getRestaurant().getId());
            reservationDto.setRestaurantName(reservation.getRestaurant().getName());

            reservationDto.setAttractionId(reservation.getAttraction().getId());
            reservationDto.setAttractionName(reservation.getAttraction().getName());

            response.add(reservationDto);
        }
        return response;
    }

    @Override
    public ReservationDto getReservationById(Integer id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with id: " + id + " not found"));

        ReservationDto response = new ReservationDto();

        response.setReservationId(reservation.getId());
        response.setReservationUserName(reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName());
        response.setReservationType(reservation.getReservationType());

        response.setHotelId(reservation.getHotel().getId());
        response.setHotelName(reservation.getHotel().getName());

        response.setRestaurantId(reservation.getRestaurant().getId());
        response.setRestaurantName(reservation.getRestaurant().getName());

        response.setAttractionId(reservation.getAttraction().getId());
        response.setAttractionName(reservation.getAttraction().getName());

        return response;
    }

    @Override
    public Reservation createReservationByUser(CreateReservationDto request) {
        Reservation reservation = new Reservation();
        ReservationDetail details = new ReservationDetail(
                request.getHotelRoomNumber(),
                request.getRestaurantTableNumber()
        );

        //If agency id is present -> true : else -> false
        reservation.setAgencyReservation(request.getAgencyId() != null);

        AppUser user = userService.getUserById(request.getReservationUserId());
        reservation.setUser(user);

        reservation.setReservationType(request.getReservationType());

        switch (request.getReservationType()) {
            case HotelReservation -> reservation.setHotel(
                    hotelService.getHotel(request.getHotelId())
            );
            case RestaurantReservation -> reservation.setRestaurant(
                    restaurantService.getRestaurant(request.getRestaurantId())
            );
            case ATTRACTION_Reservation -> reservation.setAttraction(
                    attractionService.getTouristAttraction(request.getAttractionId())
            );
        }

        reservation.setReservationDetail(details);

        reservation.setFromDate(request.getFromDate());
        reservation.setToDate(request.getToDate());

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation createReservationByAgency(CreateReservationDto request) {
        Reservation reservation = new Reservation();
        ReservationDetail details = new ReservationDetail(
                request.getHotelRoomNumber(),
                request.getRestaurantTableNumber()
        );

        //If agency id is present -> true : else -> false
        reservation.setAgencyReservation(request.getAgencyId() != null);

        AppUser user = userService.getUserById(request.getReservationUserId());
        reservation.setUser(user);

        reservation.setReservationType(request.getReservationType());


        switch (request.getReservationType()) {
            case HotelReservation -> reservation.setHotel(
                    hotelService.getHotel(request.getHotelId())
            );
            case RestaurantReservation -> reservation.setRestaurant(
                    restaurantService.getRestaurant(request.getRestaurantId())
            );
            case ATTRACTION_Reservation -> reservation.setAttraction(
                    attractionService.getTouristAttraction(request.getAttractionId())
            );
        }

        reservation.setReservationDetail(details);

        reservation.setFromDate(request.getFromDate());
        reservation.setToDate(request.getToDate());

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Integer reservationId, CreateReservationDto request) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with id: " + reservationId + " not found."));

        ReservationDetail details = new ReservationDetail(
                request.getHotelRoomNumber(),
                request.getRestaurantTableNumber()
        );
        reservation.setReservationDetail(details);
        reservation.setReservationType(request.getReservationType());

        switch (request.getReservationType()) {
            case HotelReservation -> reservation.setHotel(
                    hotelService.getHotel(request.getHotelId())
            );
            case RestaurantReservation -> reservation.setRestaurant(
                    restaurantService.getRestaurant(request.getRestaurantId())
            );
            case ATTRACTION_Reservation -> reservation.setAttraction(
                    attractionService.getTouristAttraction(request.getAttractionId())
            );
        }

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
}
