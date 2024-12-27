package com.spu.TourismApp.Shared.Dto;

import com.spu.TourismApp.Models.Utils.TouristAttractionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TouristAttractionDto extends CreateTouristAttractionDto implements Serializable {

    Integer id;

    public TouristAttractionDto(Integer id,
                                String name,
                                String description,
                                String address,
                                String phone,
                                String imageUrl) {
        super(name, description, address, phone, imageUrl);
        this.id = id;
    }
}
