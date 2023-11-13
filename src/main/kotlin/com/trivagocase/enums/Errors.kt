package com.trivagocase.enums

enum class Errors (val code: String, val message: String) {

    HOTELNOTFOUND("HOTELNOTFOUND","Hotel [%s] not found."),
    HOTELNAME("HOTELNAME", "The hotel name cannot contain the words 'free', 'offer', 'book' or 'website' and sould be longer than 10 characters."),
    HOTELRATING("HOTELRATING", "The hotel rating must be between 0 and 5."),
    HOTELCATEGORY("HOTELCATEGORY", "The hotel category must be one of 'hotel', 'alternative', 'hostel', 'lodge', 'resort' or 'guest-house'."),
    LOCATIONZIPCODE("LOCATIONZIPCODE", "The hotel location zipcode must have a length of 5."),
    HOTELIMAGE("HOTELIMAGE", "The hotel image must be a valid URL."),
    HOTELREPUTATION("HOTELREPUTATION", "The hotel reputation must be between 0 and 1000."),
    HOTELAVAILABILITY("HOTELAVAILABILITY", "There is no availability.")
}