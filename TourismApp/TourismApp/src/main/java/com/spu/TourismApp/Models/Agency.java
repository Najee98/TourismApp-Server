package com.spu.TourismApp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agencies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String address;
    String phone;
    String imageUrl;

//    @ManyToMany(mappedBy = "agencies")
//    List<AppUser> subscribers;
}
