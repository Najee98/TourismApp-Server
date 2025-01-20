package com.spu.TourismApp.Shared.Dto.TravellingAgency;

import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgencyTourDto implements Serializable {

    Integer tourId;
    Integer agencyId;
    String agencyName;
    String tourName;
    Date startDate;
    Date endDate;
    List<ReservationDto> reservations;
}
