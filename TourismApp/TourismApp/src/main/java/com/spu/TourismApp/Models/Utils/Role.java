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
                    USER_LOGIN,
                    USER_REGISTER,
                    USER_VIEW_ATTRACTIONS,
                    USER_CREATE_RESERVATION,
                    USER_GET_RESERVATION,
                    USER_VIEW_RESERVATIONS,
                    USER_CANCEL_RESERVATION,
                    HTL_MGR_VIEW_HOTELS,
                    HTL_MGR_GET_HOTEL,
                    RST_MGR_VIEW_RESTAURANTS,
                    RST_MGR_GET_RESTAURANTS,
                    AGN_MGR_VIEW_AGENCY_ATTRACTIONS,
                    AGN_MGR_VIEW_AGENCIES,
                    AGN_MGR_GET_AGENCY

            )
    ),

    RESTAURANT_MANAGER(
            Set.of(
                    USER_LOGIN,
                    USER_REGISTER,

                    RST_MGR_VIEW_RESTAURANTS,
                    RST_MGR_GET_RESTAURANTS,
                    RST_MGR_CREATE_RESTAURANT,
                    RST_MGR_UPDATE_RESTAURANT,
                    RST_MGR_DELETE_RESTAURANT
            )
    ),
    HOTEL_MANAGER(
            Set.of(
                    USER_LOGIN,
                    USER_REGISTER,

                    HTL_MGR_VIEW_HOTELS,
                    HTL_MGR_GET_HOTEL,
                    HTL_MGR_CREATE_HOTEL,
                    HTL_MGR_UPDATE_HOTEL,
                    HTL_MGR_DELETE_HOTEL
            )
    ),
    AGENCY_MANAGER(
            Set.of(
                    USER_LOGIN,
                    USER_REGISTER,

                    AGN_MGR_VIEW_AGENCIES,
                    AGN_MGR_GET_AGENCY,
                    AGN_MGR_VIEW_AGENCY_ATTRACTIONS,
                    AGN_MGR_CREATE_AGENCY,
                    AGN_MGR_UPDATE_AGENCY,
                    AGN_MGR_DELETE_AGENCY
            )
    ),
    ADMIN(
            Set.of(
                    USER_LOGIN,
                    USER_REGISTER,

                    USER_VIEW_ATTRACTIONS,
                    USER_CREATE_RESERVATION,
                    USER_GET_RESERVATION,
                    USER_VIEW_RESERVATIONS,
                    USER_CANCEL_RESERVATION,

                    RST_MGR_VIEW_RESTAURANTS,
                    RST_MGR_GET_RESTAURANTS,
                    RST_MGR_CREATE_RESTAURANT,
                    RST_MGR_UPDATE_RESTAURANT,
                    RST_MGR_DELETE_RESTAURANT,

                    HTL_MGR_VIEW_HOTELS,
                    HTL_MGR_GET_HOTEL,
                    HTL_MGR_CREATE_HOTEL,
                    HTL_MGR_UPDATE_HOTEL,
                    HTL_MGR_DELETE_HOTEL,

                    AGN_MGR_VIEW_AGENCIES,
                    AGN_MGR_GET_AGENCY,
                    AGN_MGR_VIEW_AGENCY_ATTRACTIONS,
                    AGN_MGR_CREATE_AGENCY,
                    AGN_MGR_UPDATE_AGENCY,
                    AGN_MGR_DELETE_AGENCY
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
