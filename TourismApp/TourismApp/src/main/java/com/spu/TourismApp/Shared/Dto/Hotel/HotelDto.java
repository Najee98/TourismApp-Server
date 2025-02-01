package com.spu.TourismApp.Shared.Dto.Hotel;

import com.spu.TourismApp.Shared.Dto.Attraction.CreateAttractionDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class HotelDto extends CreateAttractionDto implements Serializable {

    private Integer id;
    private int availableRooms;
    private Integer managerId;

    public HotelDto(String name, String description, String address, String phone, String imageUrl) {
        super(name, description, address, phone, imageUrl);
    }

    public HotelDto(Integer id,
                    String name,
                    String description,
                    String address,
                    String phone,
                    String imageUrl,
                    int availableRooms) {
        super(name, description, address, phone, imageUrl);
        this.id = id;
        this.availableRooms = availableRooms;
    }

//    public HotelDto(Integer id) {
//        this.id = id;
//    }
}
