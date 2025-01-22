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
public class ReservationDto implements Serializable {

    Integer agencyId;
    String reservationType;

    Integer reservationRelatedId;

    int tableOrRoomNumber;

    Date fromDate;
    Date toDate;
}
