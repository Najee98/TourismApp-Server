package com.spu.TourismApp.Shared.Dto.Tour;

import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTourDto {
    private String tourName;
    private List<Integer> reservationIds;
    private Date startDate;
    private Date endDate;
    private int maxSubscribersCount;
}
