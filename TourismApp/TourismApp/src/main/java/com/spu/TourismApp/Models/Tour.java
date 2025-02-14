package com.spu.TourismApp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@Entity
@Table(name = "tours")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    int maxSubscribersCount;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "agency_id", nullable = false)
    Agency agency;

    @ManyToMany(mappedBy = "tours", cascade = CascadeType.ALL)
    public Set<AppUser> subscribers = new HashSet<>();

    private Date startDate;

    private Date endDate;

}
