package com.trivagocase.controller.request

data class PostLocationRequest (
        val city: String,
        val state: String,
        val country: String,
        val zip_code: String,
        val address: String
)
