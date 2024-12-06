package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
