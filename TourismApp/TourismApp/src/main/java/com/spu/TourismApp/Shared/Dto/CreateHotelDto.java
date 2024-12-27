package com.spu.TourismApp.Shared.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelDto extends CreateTouristAttractionDto implements Serializable {

    private Integer availableRooms;

    public CreateHotelDto(String name, String description, String address, String phone, String imageUrl) {
        super(name, description, address, phone, imageUrl);
    }

    public CreateHotelDto(String name, String description, String address, String phone, String imageUrl, Integer availableRooms) {
        super(name, description, address, phone, imageUrl);
        this.availableRooms = availableRooms;
    }
}
