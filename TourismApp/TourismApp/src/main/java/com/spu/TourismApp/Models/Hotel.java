package com.spu.TourismApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spu.TourismApp.Models.Utils.TouristAttractionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String address;
    String phone;
    String imageUrl;
    Integer availableRooms;

    @JsonIgnore
    @ManyToMany(mappedBy = "hotels")
    List<Reservation> reservations;

//    TouristAttractionType type = TouristAttractionType.HOTEL;

}
