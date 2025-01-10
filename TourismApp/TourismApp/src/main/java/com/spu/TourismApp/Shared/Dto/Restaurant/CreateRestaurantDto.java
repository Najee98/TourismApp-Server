package com.spu.TourismApp.Shared.Dto.Restaurant;

import com.spu.TourismApp.Shared.Dto.TouristAttraction.CreateTouristAttractionDto;
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
