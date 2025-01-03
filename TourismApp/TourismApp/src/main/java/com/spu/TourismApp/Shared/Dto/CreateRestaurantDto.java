package com.spu.TourismApp.Shared.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantDto extends CreateTouristAttractionDto implements Serializable {

    private Integer availableTables;
}
