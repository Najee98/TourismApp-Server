package com.spu.TourismApp.Shared.Dto.Agency;

import com.spu.TourismApp.Models.Reservation;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class AgencyTourDbResult implements Serializable {

    Integer tourId;
    Integer agencyId;
    String agencyName;
    String tourName;
    Date startDate;
    Date endDate;
    List<Reservation> reservations;

    public AgencyTourDbResult(Integer tourId,
                              Integer agencyId,
                              String agencyName,
                              String tourName,
                              Date startDate,
                              Date endDate,
                              List<Reservation> reservations) {
        this.tourId = tourId;
        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.tourName = tourName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservations = reservations;
    }
}
