package com.spu.TourismApp.Shared.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RestaurantDto extends CreateTouristAttractionDto implements Serializable {

    Integer id;
    private Integer availableTables;

    public RestaurantDto(String name, String description, String address, String phone, String imageUrl) {
        super(name, description, address, phone, imageUrl);
    }

    public RestaurantDto(Integer id,
                         String name,
                         String description,
                         String address,
                         String phone,
                         String imageUrl,
                         Integer availableTables) {
        super(name, description, address, phone, imageUrl);
        this.id = id;
        this.availableTables = availableTables;
    }

//    public RestaurantDto(Integer id) {
//        this.id = id;
//    }
}
