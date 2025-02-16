package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Shared.Dto.Agency.ManagementUserDto;
import com.spu.TourismApp.Shared.Dto.Hotel.CreateHotelDto;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelDto;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelReservationDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;

import java.util.List;

public interface HotelService {

    List<HotelDto> getAllHotels();

    HotelDto getHotelDetails(Integer id);

    Hotel getHotel(Integer id);

    Hotel createHotel(CreateHotelDto request);

    Hotel updateHotel(HotelDto request);

    void deleteHotel(Integer id);

    List<ReservationDetailsDto> getHotelReservations();

}
