package com.spu.TourismApp.Shared.Dto.TravellingAgency;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TravellingAgencyDto extends CreateTravellingAgencyDto implements Serializable {

    Integer id;

    public TravellingAgencyDto(Integer id,
                               String name,
                               String address,
                               String phone,
                               String imageUrl) {
        super(name, address, phone, imageUrl);
        this.id = id;
    }

}
