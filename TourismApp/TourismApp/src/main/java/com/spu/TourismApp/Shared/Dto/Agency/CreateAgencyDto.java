package com.spu.TourismApp.Shared.Dto.Agency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAgencyDto implements Serializable {

    String name;
    String address;
    String phone;
    String imageUrl;
    Integer managerId;
}
