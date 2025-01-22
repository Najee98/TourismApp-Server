package com.spu.TourismApp.Shared.Dto.Reservation;

import com.spu.TourismApp.Models.Utils.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantReservationDto implements Serializable {

    Integer reservationUserId;
//    //sent null in case of user reservation
//    Integer agencyId;
    ReservationType reservationType;

    Integer restaurantId;
    int restaurantTableNumber;

    Date fromDate;
    Date toDate;
}
