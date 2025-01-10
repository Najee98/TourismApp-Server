package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

//    @Query("select new com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantDto(" +
//            "r.id," +
//            "r.name," +
//            "r.address," +
//            "r.phone," +
//            "r.imageUrl) " +
//            "from Restaurant r ")
//    List<RestaurantDto> findAllRestaurants();
//
//    @Query("select new com.spu.TourismApp.Shared.Dto.Restaurant.RestaurantDto(" +
//            "r.id," +
//            "r.name," +
//            "r.address," +
//            "r.phone," +
//            "r.imageUrl) " +
//            "from Restaurant r " +
//            "where r.id = :id")
//    RestaurantDto findRestaurantById(@Param("id") int id);
}
