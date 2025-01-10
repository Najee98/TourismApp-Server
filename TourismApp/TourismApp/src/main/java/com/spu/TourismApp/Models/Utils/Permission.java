package com.spu.TourismApp.Models.Utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    USER_REGISTER("auth:register"),
    USER_LOGIN("auth:login"),
    USER_VIEW_ATTRACTIONS("attractions:viewAttractions"),
//    USER_VIEW_AGENCIES("agencies:viewAgencies"),
//    USER_VIEW_AGENCY_ATTRACTIONS("agencies:viewAgencyAttractions"),
//    USER_VIEW_HOTELS("hotels:viewHotels"),
//    USER_GET_HOTEL("hotels:getHotel"),
    USER_CREATE_RESERVATION("reservations:createReservation"),
    USER_VIEW_RESERVATIONS("reservations:viewReservations"),
    USER_GET_RESERVATION("reservations:getReservation"),
    USER_CANCEL_RESERVATION("reservations:cancelReservation"),
//    USER_VIEW_RESTAURANTS("restaurants:viewRestaurants"),
//    USER_GET_RESTAURANT("restaurants:getRestaurant"),

    //ADMIN_VIEW_USERS("admin:viewUsers"),

//    ADMIN_VIEW_RESERVATIONS("reservations:viewReservations"),
//    ADMIN_CREATE_RESERVATION("reservations:createReservation"),
//    ADMIN_UPDATE_RESERVATION("reservations:updateReservation"),
//    ADMIN_DELETE_RESERVATION("reservations:deleteReservation"),
//    ADMIN_VIEW_HOTELS("hotels:viewHotels"),
//    ADMIN_GET_HOTEL("hotels:getHotel"),
//    ADMIN_CREATE_HOTEL("hotels:createHotel"),
//    ADMIN_UPDATE_HOTEL("hotels:updateHotel"),
//    ADMIN_DELETE_HOTEL("hotels:deleteHotel"),
//    ADMIN_VIEW_RESTAURANTS("restaurants:viewRestaurants"),
//    ADMIN_GET_RESTAURANTS("restaurants:getRestaurant"),
//    ADMIN_CREATE_RESTAURANT("restaurants:createRestaurant"),
//    ADMIN_UPDATE_RESTAURANT("restaurants:updateRestaurant"),
//    ADMIN_DELETE_RESTAURANT("restaurants:deleteRestaurant"),
//    ADMIN_VIEW_AGENCIES("agencies:viewAgencies"),
//    ADMIN_VIEW_AGENCY_ATTRACTIONS("agencies:viewAgencyAttractions"),
//    ADMIN_GET_AGENCY("agencies:getAgency"),
//    ADMIN_CREATE_AGENCY("agencies:createAgency"),
//    ADMIN_UPDATE_AGENCY("agencies:updateAgency"),
//    ADMIN_DELETE_AGENCY("agencies:deleteAgency"),

    AGN_MGR_VIEW_AGENCIES("agencies:viewAgencies"),
    AGN_MGR_VIEW_AGENCY_ATTRACTIONS("agencies:viewAgencyAttractions"),
    AGN_MGR_GET_AGENCY("agencies:getAgency"),
    AGN_MGR_CREATE_AGENCY("agencies:createAgency"),
    AGN_MGR_UPDATE_AGENCY("agencies:updateAgency"),
    AGN_MGR_DELETE_AGENCY("agencies:deleteAgency"),

    HTL_MGR_VIEW_HOTELS("hotels:viewHotels"),
    HTL_MGR_GET_HOTEL("hotels:getHotel"),
    HTL_MGR_CREATE_HOTEL("hotels:createHotel"),
    HTL_MGR_UPDATE_HOTEL("hotels:updateHotel"),
    HTL_MGR_DELETE_HOTEL("hotels:deleteHotel"),

    RST_MGR_VIEW_RESTAURANTS("restaurants:viewRestaurants"),
    RST_MGR_GET_RESTAURANTS("restaurants:getRestaurant"),
    RST_MGR_CREATE_RESTAURANT("restaurants:createRestaurant"),
    RST_MGR_UPDATE_RESTAURANT("restaurants:updateRestaurant"),
    RST_MGR_DELETE_RESTAURANT("restaurants:deleteRestaurant"),



    ;

    @Getter
    private final String permission;
}
