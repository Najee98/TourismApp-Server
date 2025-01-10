package com.spu.TourismApp.Shared.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTouristAttractionDto implements Serializable {

    String name;
    String description;
    String address;
    String phone;
    String imageUrl;
}
