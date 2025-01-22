package com.spu.TourismApp.Shared.Dto.Attraction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAttractionDto implements Serializable {

    String name;
    String description;
    String address;
    String phone;
    String imageUrl;
}
