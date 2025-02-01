package com.spu.TourismApp.Shared.Dto.Hotel;

import com.spu.TourismApp.Shared.Dto.Attraction.CreateAttractionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelDto extends CreateAttractionDto implements Serializable {

    private int availableRooms;
    private Integer managerId;

    public CreateHotelDto(String name, String description, String address, String phone, String imageUrl) {
        super(name, description, address, phone, imageUrl);
    }

    public CreateHotelDto(String name, String description, String address, String phone, String imageUrl, Integer availableRooms, Integer managerId) {
        super(name, description, address, phone, imageUrl);
        this.availableRooms = availableRooms;
        this.managerId = managerId;
    }
}
