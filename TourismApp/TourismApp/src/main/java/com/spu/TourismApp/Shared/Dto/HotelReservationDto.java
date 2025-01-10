package com.spu.TourismApp.Shared.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HotelReservationDto implements Serializable {

    Integer reservationId;
    Integer hotelId;
    String reservationUser;
    String hotelName;
    Integer roomNumber;
    Date fromDate;
    Date toDate;
    boolean isAgencyReservation;

}
