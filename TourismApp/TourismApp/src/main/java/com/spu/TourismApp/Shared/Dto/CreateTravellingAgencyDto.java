package com.spu.TourismApp.Shared.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTravellingAgencyDto {

    String name;
    String address;
    String phone;
    String imageUrl;

}
