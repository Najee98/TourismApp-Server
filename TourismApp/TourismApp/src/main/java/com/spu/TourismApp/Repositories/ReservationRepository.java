package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelReservationDto;
import com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantReservationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("select r from Reservation r " +
            "where (r.fromDate > :fromDate or r.fromDate = :fromDate)" +
            "and (r.toDate < :toDate or r.toDate = :toDate) ")
    List<Reservation> fetchIntersectedReservations(@Param("fromDate") Date fromDate,
                                                   @Param("toDate") Date toDate);

    @Query("select new com.spu.TourismApp.Shared.Dto.Hotel.HotelReservationDto(" +
            "r.id," +
            "h.id," +
            "r.user.firstName," +
            "h.name," +
            "r.fromDate," +
            "r.toDate," +
            "r.agencyReservation) " +
            "from Reservation r join r.hotel h " +
            "where r.hotel.id = :hotelId")
    List<HotelReservationDto> getHotelReservations(@Param("hotelId") Integer hotelId);

    @Query("select new com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantReservationDto(" +
            "rs.id," +
            "rt.id," +
            "rs.user.firstName," +
            "rt.name," +
            "rs.fromDate," +
            "rs.toDate," +
            "rs.agencyReservation) " +
            "from Reservation rs join rs.restaurant rt " +
            "where rs.restaurant.id = :restaurantId")
    List<RestaurantReservationDto> getRestaurantReservations(@Param("restaurantId") Integer restaurantId);

//    @Query("select new com.spu.TourismApp.Shared.Dto.Reservation.ReservationDto( " +
//            "r.id, " +
//            "r.user.firstName, " +
//            "r.reservationType, " +
//            "r.attractions, " +
//            "r.restaurants, " +
//            "r.hotels) " +
//            "from Reservation r")
//    List<ReservationDto> findAllReservations();
//
//    @Query("select new com.spu.TourismApp.Shared.Dto.Reservation.ReservationDto( " +
//            "r.id, " +
//            "r.user.firstName, " +
//            "r.reservationType, " +
//            "r.attractions, " +
//            "r.restaurants, " +
//            "r.hotels) " +
//            "from Reservation r " +
//            "where r.id = :id")
//    ReservationDto findReservationById(@Param("id") Integer id);

}
