package com.spu.TourismApp.Models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "agencies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String address;
    String phone;
    String imageUrl;

    @OneToOne(cascade = CascadeType.PERSIST)
//    @JsonManagedReference
    AppUser manager;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Tour> tour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return Objects.equals(id, agency.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
