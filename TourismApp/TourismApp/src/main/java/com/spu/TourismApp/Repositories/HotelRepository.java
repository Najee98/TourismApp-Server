package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {


//    @Query("select new com.spu.TourismApp.Shared.Dto.Hotel.HotelReservationDto(" +
//            "r.id," +
//            "h.id," +
//            "concat(r.user.firstName)," +
//            "h.name," +
//            "r. ) " +
//            "from Hotel h join h.reservations r ")
//    List<HotelReservationDto> getHotelReservations(@Param("hotelId") Integer hotelId);

//    @Query("select new com.spu.TourismApp.Shared.Dto.Hotel.HotelDto(" +
//            "h.id," +
//            "h.name," +
//            "h.description, " +
//            "h.address," +
//            "h.phone," +
//            "h.imageUrl," +
//            "h.availableRooms) " +
//            "from Hotel h ")
//    List<HotelDto> findAllHotels();
//
//    @Query("select new com.spu.TourismApp.Shared.Dto.Hotel.HotelDto(" +
//            "h.id," +
//            "h.name," +
//            "h.description, " +
//            "h.address," +
//            "h.phone," +
//            "h.imageUrl," +
//            "h.availableRooms) " +
//            "from Hotel h " +
//            "where h.id = :id")
//    HotelDto findHotelById(@Param("id") int id);
}
