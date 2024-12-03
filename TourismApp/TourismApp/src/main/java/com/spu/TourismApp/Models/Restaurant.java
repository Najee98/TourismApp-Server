package com.spu.TourismApp.Models;

import com.spu.TourismApp.Models.Utils.TouristAttractionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant extends TouristAttraction{

    Integer availableTables;

    TouristAttractionType type = TouristAttractionType.RESTAURANT;
}
