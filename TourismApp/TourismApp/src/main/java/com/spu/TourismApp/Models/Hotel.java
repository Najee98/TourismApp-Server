package com.spu.TourismApp.Models;

import com.spu.TourismApp.Models.Utils.TouristAttractionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel extends TouristAttraction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String address;
    String phone;
    String imageUrl;
    Integer availableRooms;

    @ManyToMany(mappedBy = "hotels")
    List<Reservation> reservations;

//    TouristAttractionType type = TouristAttractionType.HOTEL;

}
