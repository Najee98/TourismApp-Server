package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Shared.Dto.ReservationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

//    @Query("select new com.spu.TourismApp.Shared.Dto.ReservationDto( " +
//            "r.id, " +
//            "r.user.firstName, " +
//            "r.reservationType, " +
//            "r.attractions, " +
//            "r.restaurants, " +
//            "r.hotels) " +
//            "from Reservation r")
//    List<ReservationDto> findAllReservations();
//
//    @Query("select new com.spu.TourismApp.Shared.Dto.ReservationDto( " +
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
