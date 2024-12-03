package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.TravellingAgency;
import com.spu.TourismApp.Shared.Dto.TravellingAgencyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellingAgencyRepository extends JpaRepository<TravellingAgency, Integer> {

    @Query("select new com.spu.TourismApp.Shared.Dto.TravellingAgencyDto(" +
            "a.id," +
            "a.name," +
            "a.address," +
            "a.phone," +
            "a.imageUrl) " +
            "from TravellingAgency a")
    List<TravellingAgencyDto> findAllAgencies();
}
