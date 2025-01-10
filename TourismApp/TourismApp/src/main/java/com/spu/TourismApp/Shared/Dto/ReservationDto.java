package com.spu.TourismApp.Shared.Dto;

import com.spu.TourismApp.Models.Hotel;
import com.spu.TourismApp.Models.Restaurant;
import com.spu.TourismApp.Models.TouristAttraction;
import com.spu.TourismApp.Models.Utils.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto implements Serializable {

    Integer reservationId;
    String reservationUserName;
    ReservationType reservationType;

    Integer attractionId;
    String attractionName;

    Integer restaurantId;
    String restaurantName;


    Integer hotelId;
    String hotelName;

//    public ReservationDto(Integer reservationId,
//                          String reservationUserName,
//                          ReservationType reservationType,
//                          List<TouristAttraction> reservationTouristAttractions,
//                          List<Restaurant> reservationRestaurants,
//                          List<Hotel> reservationHotels) {
//        this.reservationId = reservationId;
//        this.reservationUserName = reservationUserName;
//        this.reservationType = reservationType;
//        this.reservationTouristAttractions = reservationTouristAttractions;
//        this.reservationRestaurants = reservationRestaurants;
//        this.reservationHotels = reservationHotels;
//    }
}
