package com.spu.TourismApp.Shared.Dto.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelReservationDto implements Serializable {

    Integer reservationId;
    Integer hotelId;
    Integer agencyId;
//    String reservationUser;
    String hotelName;
    Date fromDate;
    Date toDate;

}
