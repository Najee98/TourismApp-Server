package com.spu.TourismApp.Shared.Dto.Restaurant;

import com.spu.TourismApp.Shared.Dto.Attraction.CreateAttractionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantDto extends CreateAttractionDto implements Serializable {

    private int availableTables;
}
