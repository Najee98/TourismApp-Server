package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Agency;
import com.spu.TourismApp.Shared.Dto.Agency.AgencyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Integer> {

    @Query("select new com.spu.TourismApp.Shared.Dto.Agency.AgencyDto(" +
            "a.id," +
            "a.name," +
            "a.address," +
            "a.phone," +
            "a.imageUrl," +
            "a.manager.id) " +
            "from Agency a")
    List<AgencyDto> findAllAgencies();

//    @Query("select new com.spu.TourismApp.Shared.Dto.TouristAttraction.TouristAttractionDto(" +
//            "at.id," +
//            "at.name," +
//            "at.description," +
//            "at.address," +
//            "at.phone," +
//            "at.imageUrl) " +
//            "from TouristAttraction at join at.agencies ag " +
//    //        "on ag.attractions " +
//            "where ag.id = :agencyId")
//    List<TouristAttractionDto> getAgencyAttractions(@Param("agencyId") Integer agencyId);
}
