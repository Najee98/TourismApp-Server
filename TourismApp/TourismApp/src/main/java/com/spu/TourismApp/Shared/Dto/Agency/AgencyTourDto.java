package com.spu.TourismApp.Shared.Dto.Agency;

import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class AgencyTourDto implements Serializable {

    Integer tourId;
    Integer agencyId;
    String agencyName;
    String tourName;
    Date startDate;
    Date endDate;
    List<ReservationDetailsDto> reservations;

    public AgencyTourDto(Integer tourId, Integer agencyId, String agencyName, String tourName, Date startDate, Date endDate) {
        this.tourId = tourId;
        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.tourName = tourName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AgencyTourDto(Integer tourId, Integer agencyId, String agencyName, String tourName, Date startDate, Date endDate, List<ReservationDetailsDto> reservations) {
        this.tourId = tourId;
        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.tourName = tourName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservations = reservations;
    }
}
