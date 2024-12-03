package com.spu.TourismApp.Models;

import com.spu.TourismApp.Models.Utils.ReservationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    ReservationType reservationType;

    boolean agencyReservation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reservation_attractions",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "attraction_id"))
    List<TouristAttraction> attractions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reservation_agency",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "agency_id"))
    List<TravellingAgency> agencies;

    @ManyToOne
    @JoinColumn(name = "user_id")
    AppUser user;
}
