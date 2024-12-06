package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Shared.Dto.HotelDto;
import com.spu.TourismApp.Shared.Dto.TouristAttractionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    @Query("select new com.spu.TourismApp.Shared.Dto.HotelDto(" +
            "h.id," +
            "h.name," +
            "h.address," +
            "h.phone," +
            "h.imageUrl) " +
            "from TouristAttraction h ")
    List<HotelDto> findAllHotels();

    @Query("select new com.spu.TourismApp.Shared.Dto.HotelDto(" +
            "h.id," +
            "h.name," +
            "h.address," +
            "h.phone," +
            "h.imageUrl) " +
            "from Hotel h " +
            "where h.id = :id")
    HotelDto findHotelById(@Param("id") int id);
}
