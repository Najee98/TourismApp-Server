package com.spu.TourismApp.Shared.Dto;

import com.spu.TourismApp.Models.Utils.TouristAttractionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTouristAttractionDto implements Serializable {

    String name;
    String address;
    String phone;
    String imageUrl;
}
