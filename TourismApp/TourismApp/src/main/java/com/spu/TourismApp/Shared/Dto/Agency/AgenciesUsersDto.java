package com.spu.TourismApp.Shared.Dto.Agency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgenciesUsersDto implements Serializable {

    private Integer id;
    private String name;

}
