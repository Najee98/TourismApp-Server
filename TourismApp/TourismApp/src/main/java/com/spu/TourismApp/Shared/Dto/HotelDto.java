package com.spu.TourismApp.Shared.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class HotelDto extends CreateTouristAttractionDto implements Serializable {

    private Integer id;
    private Integer availableRooms;

    public HotelDto(String name, String description, String address, String phone, String imageUrl) {
        super(name, description, address, phone, imageUrl);
    }

    public HotelDto(Integer id,
                    String name,
                    String description,
                    String address,
                    String phone,
                    String imageUrl,
                    Integer availableRooms) {
        super(name, description, address, phone, imageUrl);
        this.id = id;
        this.availableRooms = availableRooms;
    }

//    public HotelDto(Integer id) {
//        this.id = id;
//    }
}
