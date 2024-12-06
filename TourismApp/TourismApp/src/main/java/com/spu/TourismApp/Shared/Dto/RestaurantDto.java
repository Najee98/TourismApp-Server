package com.spu.TourismApp.Shared.Dto;

import java.io.Serializable;

public class RestaurantDto extends CreateTouristAttractionDto implements Serializable {

    private Integer id;

    public RestaurantDto(Integer id, String name, String address, String phone, String imageUrl) {
        super(name, address, phone, imageUrl);
        this.id = id;
    }

    public RestaurantDto(Integer id) {
        this.id = id;
    }
}
