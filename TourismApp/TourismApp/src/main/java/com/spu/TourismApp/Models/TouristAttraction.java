package com.spu.TourismApp.Models;

import com.spu.TourismApp.Models.Utils.TouristAttractionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "toutist_attractions")
@Data
@NoArgsConstructor
public class TouristAttraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String address;
    String phone;
    TouristAttractionType type;
    String imageUrl;

    @ManyToMany(mappedBy = "attractions")
    List<Reservation> reservations;

    public TouristAttraction(Integer id, String name, String address, String phone, String imageUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.type = TouristAttractionType.OTHER;
    }
}
