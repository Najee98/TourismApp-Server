package com.spu.TourismApp.Shared.Dto.Reservation;

import com.spu.TourismApp.Models.Utils.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetailsDto implements Serializable {

    Integer reservationId;
    Integer agencyId;
    ReservationType reservationType;

    Integer relatedId;
    String relatedName;

    Date fromDate;
    Date toDate;

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
