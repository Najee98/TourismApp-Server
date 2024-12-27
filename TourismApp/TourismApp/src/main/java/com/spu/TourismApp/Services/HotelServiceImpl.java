package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Repositories.HotelRepository;
import com.spu.TourismApp.Shared.Dto.HotelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;


    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();

        List<HotelDto> response = new ArrayList<>();

        for (Hotel hotel : hotels) {
            HotelDto hotelDto = new HotelDto();

            hotelDto = mapHotelToDto(hotel);

            response.add(hotelDto);
        }

        return response;
    }

    @Override
    public HotelDto getHotelDetails(Integer id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id: " + id + " not found"));

        HotelDto response = new HotelDto();

        response = mapHotelToDto(hotel);

        return response;
    }

    @Override
    public Hotel getHotel(Integer id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id: " + id + " not found"));
    }

    @Override
    public Hotel createHotel(HotelDto request) {
        Hotel hotel = new Hotel();

        hotel = mapDtoToHotel(hotel, request);

        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Integer id, HotelDto request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id: " + id + " not found."));

        hotel = mapDtoToHotel(hotel, request);

        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(Integer id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id: " + id + " not found."));

        hotelRepository.delete(hotel);
    }

    private static Hotel mapDtoToHotel(Hotel hotel, HotelDto dto) {

        hotel.setName(dto.getName());
        hotel.setDescription(dto.getDescription());
        hotel.setAddress(dto.getAddress());
        hotel.setPhone(dto.getPhone());
        hotel.setImageUrl(dto.getImageUrl());
        hotel.setAvailableRooms(dto.getAvailableRooms());

        return hotel;
    }

    private static HotelDto mapHotelToDto(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();

        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setDescription(hotel.getDescription());
        hotelDto.setAddress(hotel.getAddress());
        hotelDto.setPhone(hotel.getPhone());
        hotelDto.setImageUrl(hotel.getImageUrl());
        hotelDto.setAvailableRooms(hotel.getAvailableRooms());

        return hotelDto;
    }
}
