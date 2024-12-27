package com.spu.TourismApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spu.TourismApp.Models.Utils.TouristAttractionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restautants")
public class Restaurant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String address;
    String phone;
    String imageUrl;
    Integer availableTables;

    @JsonIgnore
    @ManyToMany(mappedBy = "restaurants")
    List<Reservation> reservations;

}
