package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

//    @Query("select new com.spu.TourismApp.Shared.Dto.TouristAttraction.TouristAttractionDto(" +
//            "ta.id," +
//            "ta.name," +
//            "ta.address," +
//            "ta.phone," +
//            "ta.imageUrl) " +
//            "from TouristAttraction ta ")
//    List<TouristAttractionDto> findAllAttractions();
//
//    @Query("select new com.spu.TourismApp.Shared.Dto.TouristAttraction.TouristAttractionDto(" +
//            "ta.id," +
//            "ta.name," +
//            "ta.address," +
//            "ta.phone," +
//            "ta.imageUrl) " +
//            "from TouristAttraction ta " +
//            "where ta.id = :id")
//    TouristAttractionDto findAttractionById(@Param("id") int id);
}
