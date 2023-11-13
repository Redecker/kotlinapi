package com.trivagocase.extension

import com.trivagocase.controller.request.PostHotelRequest
import com.trivagocase.controller.request.PostLocationRequest
import com.trivagocase.controller.request.PutHotelRequest
import com.trivagocase.controller.request.PutLocationRequest
import com.trivagocase.controller.response.HotelResponse
import com.trivagocase.controller.response.LocationResponse
import com.trivagocase.model.HotelModel
import com.trivagocase.model.LocationModel

fun PostHotelRequest.toHotelModel(): HotelModel {

    return HotelModel(name = this.name,
                      rating = this.rating,
                      category = this.category,
                      location = this.location.toLocationModel(),
                      image = this.image,
                      reputation = this.reputation,
                      price = this.price,
                      availability = this.availability)
}

fun PostLocationRequest.toLocationModel() : LocationModel{
    return LocationModel(city = this.city,
                         state = this.state,
                         country = this.country,
                         zip_code = this.zip_code,
                         address = this.address)
}

fun PutHotelRequest.toHotelModel(previousValue: HotelModel): HotelModel {

    val hotel = HotelModel(
            id = previousValue.id,
            name = this.name,
            rating = this.rating,
            category = this.category,
            location = this.location.toLocationModel(previousValue),
            image = this.image,
            reputation = this.reputation,
            price = this.price,
            availability = this.availability)
    hotel.reputationBadge = previousValue.reputationBadge
    return hotel
}

fun PutLocationRequest.toLocationModel(previousValue: HotelModel) : LocationModel{
    return LocationModel(
            id = previousValue.location?.id,
            city = this.city,
            state = this.state,
            country = this.country,
            zip_code = this.zip_code,
            address = this.address)
}

fun HotelModel.toResponse(): HotelResponse {
    return HotelResponse(
        id = this.id,
        name = this.name,
        rating = this.rating,
        category = this.category,
        location = this.location!!.toResponse(),
        image = this.image,
        reputation = this.reputation,
        reputationBadge = this.reputationBadge,
        price = this.price,
        availability = this.availability
    )
}

fun LocationModel.toResponse() : LocationResponse{
    return LocationResponse(city = this.city,
        state = this.state,
        country = this.country,
        zip_code = this.zip_code,
        address = this.address)
}