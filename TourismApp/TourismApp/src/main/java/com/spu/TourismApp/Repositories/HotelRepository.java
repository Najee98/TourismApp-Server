package com.spu.TourismApp.Repositories;

import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelReservationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
