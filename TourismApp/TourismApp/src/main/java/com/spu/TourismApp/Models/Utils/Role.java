package com.spu.TourismApp.Models.Utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.spu.TourismApp.Models.Utils.Permission.*;

@RequiredArgsConstructor
public enum Role {

    USER(
            Set.of(
                    LOGIN,
                    REGISTER,
                    VIEW_USER_TOURS,
                    VIEW_AGENCIES,
                    VIEW_AGENCY_TOURS,
                    VIEW_HOTELS,
                    GET_HOTEL,
                    VIEW_RESTAURANTS,
                    GET_RESTAURANT,
                    VIEW_ATTRACTIONS,
                    GET_ATTRACTION,
                    ADD_USER_TO_TOUR,
                    REMOVE_USER_FROM_TOUR,
                    VIEW_ALL_TOURS,
                    VIEW_TOUR_RESERVATIONS,
                    GET_RESERVATION
            )
    ),

    RESTAURANT_MANAGER(
            Set.of(
                    LOGIN,
                    GET_RESTAURANT,
                    UPDATE_RESTAURANT,
                    DELETE_RESTAURANT,
                    VIEW_RESTAURANT_RESERVATIONS,
                    GET_RESERVATION
            )
    ),

    HOTEL_MANAGER(
            Set.of(
                    LOGIN,
                    GET_HOTEL,
                    UPDATE_HOTEL,
                    DELETE_HOTEL,
                    VIEW_HOTEL_RESERVATIONS,
                    GET_RESERVATION
            )
    ),

    AGENCY_MANAGER(
            Set.of(
                    LOGIN,
                    UPDATE_AGENCY,
                    DELETE_AGENCY,
                    VIEW_RESTAURANTS,
                    GET_RESTAURANT, //details
                    VIEW_HOTELS,
                    GET_HOTEL, //details
                    VIEW_ATTRACTIONS,
                    GET_ATTRACTION, //details
                    CREATE_RESERVATION,
                    VIEW_ALL_TOURS,
                    VIEW_AGENCY_TOURS,
                    GET_TOUR,
                    CREATE_TOUR,
                    UPDATE_TOUR,
                    DELETE_TOUR,
                    VIEW_TOUR_RESERVATIONS,
                    VIEW_AGENCY_RESERVATIONS,
                    GET_RESERVATION
            )
    ),

    ADMIN(
            Set.of(
                    LOGIN,
                    REGISTER,
                    GET_LOGGED_IN_USER_ID,
                    VIEW_USERS_FOR_AGENCIES,
                    CREATE_AGENCY,
                    VIEW_AGENCIES,
                    GET_AGENCY,
                    VIEW_AGENCY_TOURS,
                    GET_TOUR,
                    VIEW_USERS_FOR_HOTELS,
                    CREATE_HOTEL,
                    VIEW_HOTELS,
                    VIEW_USERS_FOR_RESTAURANTS,
                    CREATE_RESTAURANT,
                    VIEW_RESTAURANTS,

                    CREATE_ATTRACTION,

                    UPDATE_ATTRACTION,
                    DELETE_ATTRACTION
            ))
    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
