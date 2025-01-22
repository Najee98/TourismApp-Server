package com.spu.TourismApp.Models.Utils;

import com.spu.TourismApp.Models.Attraction;
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

    int roomNumber;
    int tableNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    List<Attraction> attractions;

    public ReservationDetail(int roomNumber, int tableNumber) {
        this.roomNumber = roomNumber;
        this.tableNumber = tableNumber;
    }
}