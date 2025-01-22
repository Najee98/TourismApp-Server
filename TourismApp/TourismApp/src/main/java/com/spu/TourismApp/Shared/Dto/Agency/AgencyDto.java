package com.spu.TourismApp.Shared.Dto.Agency;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AgencyDto extends CreateAgencyDto implements Serializable {

    Integer id;

    public AgencyDto(Integer id,
                     String name,
                     String address,
                     String phone,
                     String imageUrl) {
        super(name, address, phone, imageUrl);
        this.id = id;
    }

}
