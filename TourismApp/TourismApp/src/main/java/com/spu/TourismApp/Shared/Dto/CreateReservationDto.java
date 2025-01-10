package com.spu.TourismApp.Shared.Dto;

import com.spu.TourismApp.Models.Utils.ReservationType;
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
    Integer agencyId;
    ReservationType reservationType;

    Integer attractionId;

    Integer restaurantId;
    Integer restaurantTableNumber;

    Integer hotelId;
    Integer hotelRoomNumber;

    Date fromDate;
    Date toDate;
}
