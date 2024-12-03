package com.spu.TourismApp.Models;

import com.spu.TourismApp.Models.Utils.TouristAttractionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Hotel extends TouristAttraction{

    Integer availableRooms;

    TouristAttractionType type = TouristAttractionType.HOTEL;

}
