package com.spu.TourismApp.Shared.Dto.Restaurant;

import com.spu.TourismApp.Shared.Dto.Attraction.CreateAttractionDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RestaurantDto extends CreateAttractionDto implements Serializable {

    Integer id;
    private int availableTables;
    private Integer managerId;

    public RestaurantDto(String name, String description, String address, String phone, String imageUrl) {
        super(name, description, address, phone, imageUrl);
    }

    public RestaurantDto(Integer id,
                         String name,
                         String description,
                         String address,
                         String phone,
                         String imageUrl,
                         int availableTables,
                         Integer managerId) {
        super(name, description, address, phone, imageUrl);
        this.id = id;
        this.availableTables = availableTables;
        this.managerId = managerId;
    }

//    public RestaurantDto(Integer id) {
//        this.id = id;
//    }
}
