package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Tour;
import com.spu.TourismApp.Shared.Dto.TravellingAgency.AgencyTourDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

    @Query("select new com.spu.TourismApp.Shared.Dto.TravellingAgency.AgencyTourDto(" +
            "t.id," +
            "t.agency.id," +
            "t.agency.name," +
            "t.name," +
            "t.startDate," +
            "t.endDate," +
            "   (" +
            "       SELECT new com.spu.TourismApp.Shared.Dto.Reservation.ReservationDto(" +
            "            r.id," +
            "            CONCAT(u.firstName, ' ', u.lastName)," +
            "            r.reservationType," +
            "            a.id," +
            "            a.name," +
            "            res.id," +
            "            res.name," +
            "            h.id," +
            "            h.name)" +
            "        FROM Reservation r" +
            "            LEFT JOIN r.user u" +
            "            LEFT JOIN r.attraction a" +
            "            LEFT JOIN r.restaurant res" +
            "            LEFT JOIN r.hotel h" +
            "            WHERE r.tour.id = t.id " +
            "   )" +
            ") " +
            "FROM Tour t " +
            "WHERE t.agency.id = :agencyId")
    List<AgencyTourDto> getAgencyTours(@Param("agencyId") Integer agencyId);
}
