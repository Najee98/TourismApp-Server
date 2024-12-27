package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.*;
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
    private final TouristAttractionService attractionService;;
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
            reservationDto.setReservationHotels(reservation.getHotels());
            reservationDto.setReservationRestaurants(reservation.getRestaurants());
            reservationDto.setReservationTouristAttractions(reservation.getAttractions());

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
        response.setReservationHotels(reservation.getHotels());
        response.setReservationRestaurants(reservation.getRestaurants());
        response.setReservationTouristAttractions(reservation.getAttractions());

        return response;
    }

    @Override
    public Reservation addReservationByUser(CreateReservationDto request) {
        Reservation reservation = new Reservation();

        //If agency id is present -> true : else -> false
        reservation.setAgencyReservation(request.getReservationAgencyId() != null);

        AppUser user = userService.getUserById(request.getReservationUserId());
        reservation.setUser(user);

        List< TouristAttraction> attractions = new ArrayList<>();
        for (Integer attractionId : request.getTouristAttractionIds()){
            attractions.add(
                    attractionService.getTouristAttraction(attractionId)
            );
        }
        reservation.setAttractions(attractions);

        List<Hotel> hotels = new ArrayList<>();
        for (Integer hotelId : request.getHotelIds()){
            hotels.add(
                    hotelService.getHotel(hotelId)
            );
        }
        reservation.setHotels(hotels);

        List<Restaurant> restaurants = new ArrayList<>();
        for (Integer restaurantId : request.getRestaurantIds()){
            restaurants.add(
                    restaurantService.getRestaurant(restaurantId)
            );
        }
        reservation.setRestaurants(restaurants);

        reservation.setFromDate(request.getFromDate());
        reservation.setToDate(request.getToDate());

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation addReservationByAgency(CreateReservationDto request) {
        Reservation reservation = new Reservation();

        //If agency id is present -> true : else -> false
        reservation.setAgencyReservation(request.getReservationAgencyId() != null);

        AppUser user = userService.getUserById(request.getReservationUserId());
        reservation.setUser(user);

        List< TouristAttraction> attractions = new ArrayList<>();
        for (Integer attractionId : request.getTouristAttractionIds()){
            attractions.add(
                    attractionService.getTouristAttraction(attractionId)
            );
        }
        reservation.setAttractions(attractions);

        List<Hotel> hotels = new ArrayList<>();
        for (Integer hotelId : request.getHotelIds()){
            hotels.add(
                    hotelService.getHotel(hotelId)
            );
        }
        reservation.setHotels(hotels);

        List<Restaurant> restaurants = new ArrayList<>();
        for (Integer restaurantId : request.getRestaurantIds()){
            restaurants.add(
                    restaurantService.getRestaurant(restaurantId)
            );
        }
        reservation.setRestaurants(restaurants);

        reservation.setFromDate(request.getFromDate());
        reservation.setToDate(request.getToDate());

        return reservationRepository.save(reservation);
    }
}
