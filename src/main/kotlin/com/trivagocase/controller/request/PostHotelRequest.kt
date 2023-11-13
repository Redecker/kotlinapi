package com.trivagocase.controller.request

data class PostHotelRequest (
        val name: String,
        val rating: Int,
        val category: String,
        val location: PostLocationRequest,
        val image: String,
        val reputation: Int,
        val price: Double,
        val availability: Int
)