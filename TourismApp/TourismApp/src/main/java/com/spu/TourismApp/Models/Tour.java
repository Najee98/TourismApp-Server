package com.spu.TourismApp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tours")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(mappedBy = "tours")
    @JsonBackReference
    List<AppUser> subscribers;

    private Date startDate;

    private Date endDate;

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxSubscribersCount=" + maxSubscribersCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
