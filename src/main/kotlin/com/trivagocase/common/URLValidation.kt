package com.trivagocase.common

import java.net.URI

fun isValidUrl(urlString: String): Boolean {
    return try {
        URI(urlString)
        true
    } catch (e: Exception) {
        false
    }
}
