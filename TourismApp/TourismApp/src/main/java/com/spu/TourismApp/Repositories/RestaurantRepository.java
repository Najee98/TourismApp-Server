package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Restaurant;
import com.spu.TourismApp.Shared.Dto.HotelDto;
import com.spu.TourismApp.Shared.Dto.RestaurantDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

//    @Query("select new com.spu.TourismApp.Shared.Dto.RestaurantDto(" +
//            "r.id," +
//            "r.name," +
//            "r.address," +
//            "r.phone," +
//            "r.imageUrl) " +
//            "from Restaurant r ")
//    List<RestaurantDto> findAllRestaurants();
//
//    @Query("select new com.spu.TourismApp.Shared.Dto.RestaurantDto(" +
//            "r.id," +
//            "r.name," +
//            "r.address," +
//            "r.phone," +
//            "r.imageUrl) " +
//            "from Restaurant r " +
//            "where r.id = :id")
//    RestaurantDto findRestaurantById(@Param("id") int id);
}
