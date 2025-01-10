package com.spu.TourismApp.Shared.Dto.TravellingAgency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTravellingAgencyDto implements Serializable {

    String name;
    String address;
    String phone;
    String imageUrl;

}
