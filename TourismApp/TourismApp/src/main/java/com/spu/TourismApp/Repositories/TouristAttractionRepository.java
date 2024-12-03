package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.TouristAttraction;
import com.spu.TourismApp.Shared.Dto.TouristAttractionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristAttractionRepository extends JpaRepository<TouristAttraction, Integer> {

    @Query("select new com.spu.TourismApp.Shared.Dto.TouristAttractionDto(" +
            "ta.id," +
            "ta.name," +
            "ta.address," +
            "ta.phone," +
            "ta.type," +
            "ta.imageUrl) " +
            "from TouristAttraction ta ")
    List<TouristAttractionDto> findAllAttractions();

    @Query("select new com.spu.TourismApp.Shared.Dto.TouristAttractionDto(" +
            "ta.id," +
            "ta.name," +
            "ta.address," +
            "ta.phone," +
            "ta.type," +
            "ta.imageUrl) " +
            "from TouristAttraction ta " +
            "where ta.id = :id")
    TouristAttractionDto findAttractionById(@Param("id") int id);
}
