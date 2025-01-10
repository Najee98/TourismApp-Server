package com.spu.TourismApp.Shared.Dto.Tour;

import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDto {
    private Integer id;
    private String tourName;
    private Integer agencyId;
    private List<Integer> reservationIds;
    private Date startDate;
    private Date endDate;
}
