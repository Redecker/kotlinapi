package com.trivagocase.controller.response

class HotelResponse (
    val id: Int?,
    val name: String,
    val rating: Int,
    val category: String,
    val location: LocationResponse,
    val image: String,
    val reputation: Int,
    val reputationBadge : String,
    val price: Double,
    val availability: Int
)