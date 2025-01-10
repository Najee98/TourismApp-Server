package com.spu.TourismApp.Models.Utils;

import com.spu.TourismApp.Models.TouristAttraction;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Embeddable
@NoArgsConstructor
@Data
public class ReservationDetail {

    Integer roomNumber;
    Integer tableNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    List<TouristAttraction> attractions;

    public ReservationDetail(Integer roomNumber, Integer tableNumber) {
        this.roomNumber = roomNumber;
        this.tableNumber = tableNumber;
    }
}