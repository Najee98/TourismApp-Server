package com.spu.TourismApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@AllArgsConstructor
public class TouristAttraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String description;
    String address;
    String phone;
//    TouristAttractionType type;
    String imageUrl;

    @JsonIgnore
    @ManyToMany(mappedBy = "attractions")
    List<Reservation> reservations;

    @JsonIgnore
    @ManyToMany(mappedBy = "attractions")
    List<TravellingAgency> agencies;
}
