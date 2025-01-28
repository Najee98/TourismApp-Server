package com.spu.TourismApp.Shared.Dto.Agency;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AgencyDto extends CreateAgencyDto implements Serializable {

    Integer id;
    Integer managerId;

    public AgencyDto(Integer id,
                     String name,
                     String address,
                     String phone,
                     String imageUrl,
                     Integer managerId) {
        super(name, address, phone, imageUrl, managerId);
        this.id = id;
        this.managerId = managerId;
    }

}
