package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Shared.Dto.*;

import java.util.List;

public interface HotelService {

    List<HotelDto> getAllHotels();

    HotelDto getHotelDetails(Integer id);

    Hotel getHotel(Integer id);

    Hotel createHotel(CreateHotelDto request);

    Hotel updateHotel(HotelDto request);

    void deleteHotel(Integer id);

    List<HotelReservationDto> getHotelReservations(Integer hotelId);
}
