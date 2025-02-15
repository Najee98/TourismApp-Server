package com.spu.TourismApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spu.TourismApp.Models.Utils.ReservationDetail;
import com.spu.TourismApp.Models.Utils.ReservationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Enumerated(EnumType.STRING)
    ReservationType reservationType;

    @Embedded
    ReservationDetail reservationDetail;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel = null;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant = null;

    @ManyToOne
    @JoinColumn(name = "attraction_id")
    Attraction attraction = null;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agency_id")
    Agency agency;

    Date fromDate;
    Date toDate;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    Tour tour;
}
