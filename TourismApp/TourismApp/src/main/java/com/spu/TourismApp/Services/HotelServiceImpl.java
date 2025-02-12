package com.spu.TourismApp.Services;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.spu.TourismApp.Models.AppUser;
import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Models.Reservation;
import com.spu.TourismApp.Models.Utils.ReservationType;
import com.spu.TourismApp.Repositories.HotelRepository;
import com.spu.TourismApp.Repositories.ReservationRepository;
import com.spu.TourismApp.Services.Utils.UtilsService;
import com.spu.TourismApp.Shared.Dto.Agency.ManagementUserDto;
import com.spu.TourismApp.Shared.Dto.Hotel.CreateHotelDto;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelDto;
import com.spu.TourismApp.Shared.Dto.Hotel.HotelReservationDto;
import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;
    private final UtilsService utilsService;
    private final UserService userService;

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
    public List<ReservationDetailsDto> getHotelReservations() {

        Integer hotelId = utilsService.getLoggedInUserHotel().getId();

        List<HotelReservationDto> hotelReservationsList = reservationRepository.getHotelReservations(hotelId);

        List<ReservationDetailsDto> response = new ArrayList<>();

        for (HotelReservationDto reservation : hotelReservationsList) {
            ReservationDetailsDto dto = new ReservationDetailsDto();

            dto.setReservationType(ReservationType.HOTEL_RESERVATION);
            dto.setReservationId(reservation.getReservationId());
            dto.setRelatedName(reservation.getHotelName());
            dto.setRelatedId(reservation.getHotelId());
            dto.setAgencyId(reservation.getAgencyId());
            dto.setFromDate(reservation.getFromDate());
            dto.setToDate(reservation.getToDate());

            response.add(dto);
        }
        return response;
    }

    @Override
    public Hotel createHotel(CreateHotelDto request) {
        Hotel hotel = new Hotel();
        AppUser hotelManager = userService.getUserById(request.getManagerId());

        hotel.setName(request.getName());
        hotel.setAddress(request.getAddress());
        hotel.setPhone(request.getPhone());
        hotel.setDescription(request.getDescription());
        hotel.setImageUrl(request.getImageUrl());
        hotel.setAvailableRooms(request.getAvailableRooms());
        hotel.setManager(hotelManager);

        hotelManager.setHotel(hotel);

        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(HotelDto request) {
        Hotel hotel = hotelRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id: " + request.getId() + " not found."));

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
        hotelDto.setManagerId(hotel.getManager().getId());

        return hotelDto;
    }
}
