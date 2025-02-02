package com.spu.TourismApp.Models.Utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    REGISTER("auth:register"),
    LOGIN("auth:login"),

    VIEW_ATTRACTIONS("attractions:viewAttractions"),
    GET_ATTRACTION("attractions:getAttractions"),
    CREATE_ATTRACTION("attractions:createAttraction"),
    UPDATE_ATTRACTION("attractions:updateAttraction"),
    DELETE_ATTRACTION("attractions:deleteAttraction"),

    VIEW_RESERVATIONS("reservations:viewReservations"),
    GET_RESERVATION("reservations:getReservation"),

    GET_LOGGED_IN_USER_ID("auth:getLoggedInUserId"),

    VIEW_AGENCIES("agencies:viewAgencies"),
    VIEW_AGENCY_ATTRACTIONS("agencies:viewAgencyAttractions"),
    GET_AGENCY("agencies:getAgency"),
    CREATE_AGENCY("agencies:createAgency"),
    UPDATE_AGENCY("agencies:updateAgency"),
    DELETE_AGENCY("agencies:deleteAgency"),
    CREATE_RESERVATION("reservations:createReservation"),
    VIEW_USERS_FOR_AGENCIES("agencies:getAllUsersForAgencies"),
    VIEW_AGENCY_TOURS("agencies:viewAgencyTours"),

    VIEW_ALL_TOURS("tours:viewTours"),
    GET_TOUR("tours:getTour"),
    VIEW_USER_TOURS("tours:viewUserTours"),
    VIEW_TOUR_RESERVATIONS("tours:viewTourReservations"),
    CREATE_TOUR("tours:createTour"),
    UPDATE_TOUR("tours:updateTour"),
    DELETE_TOUR("tours:deleteTour"),
    ADD_USER_TO_TOUR("tours:addUserToTour"),
    REMOVE_USER_FROM_TOUR("tours:removeUserFromTour"),

    VIEW_HOTELS("hotels:viewHotels"),
    GET_HOTEL("hotels:getHotel"),
    CREATE_HOTEL("hotels:createHotel"),
    UPDATE_HOTEL("hotels:updateHotel"),
    DELETE_HOTEL("hotels:deleteHotel"),
    VIEW_USERS_FOR_HOTELS("hotels:getAllUsersForHotel"),
    VIEW_HOTEL_RESERVATIONS("hotels:getAllHotelReservations"),

    VIEW_RESTAURANTS("restaurants:viewRestaurants"),
    GET_RESTAURANT("restaurants:getRestaurant"),
    CREATE_RESTAURANT("restaurants:createRestaurant"),
    UPDATE_RESTAURANT("restaurants:updateRestaurant"),
    DELETE_RESTAURANT("restaurants:deleteRestaurant"),
    VIEW_USERS_FOR_RESTAURANTS("restaurants:getAllUsersForRestaurant"),
    VIEW_RESTAURANT_RESERVATIONS("restaurants:getAllReservationsForRestaurant"),



    ;

    @Getter
    private final String permission;
}
