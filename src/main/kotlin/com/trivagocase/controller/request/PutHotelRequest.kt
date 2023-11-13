package com.trivagocase.controller.request

data class PutHotelRequest (
        val name: String,
        val rating: Int,
        val category: String,
        val location: PutLocationRequest,
        val image: String,
        val reputation: Int,
        val price: Double,
        val availability: Int
)