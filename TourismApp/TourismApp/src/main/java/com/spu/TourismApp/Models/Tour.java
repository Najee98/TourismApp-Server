package com.spu.TourismApp.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "user_tours",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    @JsonBackReference
    public List<AppUser> subscribers = new ArrayList<>();

    private Date startDate;

    private Date endDate;

    public void addUserToTour(AppUser user) {
        this.subscribers.add(user);
        user.getTours().add(this);
    }

    public void removeUserFromTour(AppUser user) {
        this.subscribers.remove(user);
        user.getTours().remove(this);
    }

}
