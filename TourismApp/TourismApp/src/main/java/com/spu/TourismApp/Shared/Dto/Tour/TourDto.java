package com.spu.TourismApp.Shared.Dto.Tour;

import com.spu.TourismApp.Shared.Dto.Reservation.ReservationDetailsDto;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDto {
    private Integer id;
    private String tourName;
    private Integer agencyId;
    private List<ReservationDetailsDto> reservations;
    private Date startDate;
    private Date endDate;
}
