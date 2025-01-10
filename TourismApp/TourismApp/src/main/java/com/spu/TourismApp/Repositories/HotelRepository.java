package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Shared.Dto.HotelDto;
import com.spu.TourismApp.Shared.Dto.HotelReservationDto;
import com.spu.TourismApp.Shared.Dto.TouristAttractionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {


//    @Query("select new com.spu.TourismApp.Shared.Dto.HotelReservationDto(" +
//            "r.id," +
//            "h.id," +
//            "concat(r.user.firstName)," +
//            "h.name," +
//            "r. ) " +
//            "from Hotel h join h.reservations r ")
//    List<HotelReservationDto> getHotelReservations(@Param("hotelId") Integer hotelId);

//    @Query("select new com.spu.TourismApp.Shared.Dto.HotelDto(" +
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
//    @Query("select new com.spu.TourismApp.Shared.Dto.HotelDto(" +
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
