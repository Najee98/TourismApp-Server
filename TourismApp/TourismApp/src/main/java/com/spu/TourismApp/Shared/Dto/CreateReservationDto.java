package com.spu.TourismApp.Shared.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationDto implements Serializable {

    Integer reservationUserId;
    //sent null in case of user reservation
    Integer reservationAgencyId;
    List<Integer> touristAttractionIds;
    List<Integer> restaurantIds;
    List<Integer> hotelIds;

    Date fromDate;
    Date toDate;
}
