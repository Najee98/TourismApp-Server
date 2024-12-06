package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Repositories.HotelRepository;
import com.spu.TourismApp.Shared.Dto.CreateTouristAttractionDto;
import com.spu.TourismApp.Shared.Dto.HotelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;


    @Override
    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAllHotels();
    }

    @Override
    public HotelDto getHotel(Integer id) {
        return hotelRepository.findHotelById(id);
    }

    @Override
    public void createHotel(CreateTouristAttractionDto request) {
        Hotel hotel = new Hotel();

        hotel.setName(request.getName());
        hotel.setAddress(request.getAddress());
        hotel.setPhone(request.getPhone());
        hotel.setImageUrl(request.getImageUrl());

        hotelRepository.save(hotel);
    }

    @Override
    public void updateHotel(HotelDto request) {
        Hotel hotel = hotelRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id: " + request.getId() + " not found."));

        hotel.setName(request.getName());
        hotel.setAddress(request.getAddress());
        hotel.setPhone(request.getPhone());
        hotel.setImageUrl(request.getImageUrl());

        hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(Integer id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id: " + id + " not found."));

        hotelRepository.delete(hotel);
    }
}
