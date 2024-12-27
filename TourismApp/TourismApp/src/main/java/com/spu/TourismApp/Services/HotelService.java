package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Shared.Dto.CreateHotelDto;
import com.spu.TourismApp.Shared.Dto.CreateTouristAttractionDto;
import com.spu.TourismApp.Shared.Dto.HotelDto;
import com.spu.TourismApp.Shared.Dto.TouristAttractionDto;

import java.util.List;

public interface HotelService {

    List<HotelDto> getAllHotels();

    HotelDto getHotelDetails(Integer id);

    Hotel getHotel(Integer id);

    Hotel createHotel(HotelDto request);

    Hotel updateHotel(Integer id, HotelDto request);

    void deleteHotel(Integer id);
}
