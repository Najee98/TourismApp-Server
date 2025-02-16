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
            "r.agency.id," +
            "h.name," +
            "r.fromDate," +
            "r.toDate)" +
            "from Reservation r join r.hotel h " +
            "where r.hotel.id = :hotelId")
    List<HotelReservationDto> getHotelReservations(@Param("hotelId") Integer hotelId);

    @Query("select new com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantReservationDto(" +
            "rs.id," +
            "rt.id," +
            "rs.agency.id, " +
            "rt.name," +
            "rs.fromDate," +
            "rs.toDate)" +
            "from Reservation rs join rs.restaurant rt " +
            "where rs.restaurant.id = :restaurantId")
    List<RestaurantReservationDto> getRestaurantReservations(@Param("restaurantId") Integer restaurantId);

    @Query("select r from Reservation r where r.tour.id = :tourId")
    List<Reservation> findAllByTourId(@Param("tourId") Integer tourId);

    @Query("select r from Reservation r join r.tour t where t.id = :tourId")
    List<Reservation> getTourReservations(@Param("tourId") Integer tourId);

    @Query("select r from Reservation r join r.agency a where a.id = :agencyId")
    List<Reservation> getAgencyReservations(@Param("agencyId") Integer agencyId);

}
