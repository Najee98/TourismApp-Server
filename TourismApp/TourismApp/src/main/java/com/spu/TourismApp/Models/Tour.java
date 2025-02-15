package com.spu.TourismApp.Models;

import com.fasterxml.jackson.annotation.*;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    int maxSubscribersCount;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.PERSIST)
    List<Reservation> reservations;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agency_id")
//    @JsonManagedReference
    @JsonIgnore
    Agency agency;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_tours",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonManagedReference
    private Set<AppUser> subscribers = new HashSet<>();

    private Date startDate;

    private Date endDate;

    public void addUser(AppUser user) {
        this.subscribers.add(user);
        user.getTours().add(this);
    }

    public void removeUser(AppUser user) {
        this.subscribers.remove(user);
        user.getTours().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Objects.equals(id, tour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
