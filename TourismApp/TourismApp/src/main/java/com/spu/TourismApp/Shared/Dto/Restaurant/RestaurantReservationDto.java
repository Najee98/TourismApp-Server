package com.spu.TourismApp.Shared.Dto.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantReservationDto implements Serializable {

    Integer reservationId;
    Integer restaurantId;
    Integer agencyId;
//    String reservationUser;
    String restaurantName;
    Date fromDate;
    Date toDate;
//    boolean isAgencyReservation;
}
