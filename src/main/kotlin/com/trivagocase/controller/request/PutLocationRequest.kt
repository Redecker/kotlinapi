package com.trivagocase.controller.request

data class PutLocationRequest (
        val city: String,
        val state: String,
        val country: String,
        val zip_code: String,
        val address: String
)
