package com.spu.TourismApp.Shared.Dto;

import java.io.Serializable;

public class HotelDto extends CreateTouristAttractionDto implements Serializable {

    private Integer id;

    public HotelDto(Integer id, String name, String address, String phone, String imageUrl) {
        super(name, address, phone, imageUrl);
        this.id = id;
    }

    public HotelDto(Integer id) {
        this.id = id;
    }
}
