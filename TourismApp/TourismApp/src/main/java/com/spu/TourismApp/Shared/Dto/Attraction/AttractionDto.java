package com.spu.TourismApp.Shared.Dto.Attraction;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AttractionDto extends CreateAttractionDto implements Serializable {

    Integer id;

    public AttractionDto(Integer id,
                         String name,
                         String description,
                         String address,
                         String phone,
                         String imageUrl) {
        super(name, description, address, phone, imageUrl);
        this.id = id;
    }
}
