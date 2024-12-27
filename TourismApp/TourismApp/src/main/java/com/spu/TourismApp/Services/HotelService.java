package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Shared.Dto.CreateTouristAttractionDto;
import com.spu.TourismApp.Shared.Dto.HotelDto;
import com.spu.TourismApp.Shared.Dto.TouristAttractionDto;

import java.util.List;

public interface HotelService {

    List<HotelDto> getAllHotels();

    HotelDto getHotelDto(Integer id);

    Hotel getHotel(Integer id);

    void createHotel(CreateTouristAttractionDto request);

    void updateHotel(HotelDto request);

    void deleteHotel(Integer id);
}
