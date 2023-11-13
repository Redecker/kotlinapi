package com.trivagocase.controller.response

data class ErrorResponse(
        var httpCode: Int,
        var message: String,
        var internalCode: String
)