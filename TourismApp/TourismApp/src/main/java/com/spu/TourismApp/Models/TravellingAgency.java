package com.spu.TourismApp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "agencies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravellingAgency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String address;
    String phone;
    String imageUrl;

    @ManyToMany(mappedBy = "agencies")
    List<AppUser> subscribers;
}
