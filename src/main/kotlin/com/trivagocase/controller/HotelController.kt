package com.trivagocase.controller

import com.trivagocase.controller.request.PostHotelRequest
import com.trivagocase.controller.request.PutHotelRequest
import com.trivagocase.controller.response.HotelResponse
import com.trivagocase.extension.toHotelModel
import com.trivagocase.extension.toResponse
import com.trivagocase.service.HotelService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("hotels")
class HotelController (
    val hotelService : HotelService
){
    @GetMapping
    @Operation(
            description = "Get hotels."
    )
    fun getAllHotels(@RequestParam name: String?,
               @RequestParam rating: Int?,
               @RequestParam city: String?,
               @RequestParam reputationBadge: String?
               ): List<HotelResponse> {
        return hotelService.getAllHotels(name, rating, reputationBadge, city).map{ it.toResponse()}
    }

    @PutMapping("/{id}/availability")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            description = "Reduces the accommodation availability in 1."
    )
    fun reducesAvailability(@PathVariable id: Int) {
        hotelService.reduceAvailability(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            description = "Create an item."
    )
    fun create(@RequestBody hotel: PostHotelRequest) {
        hotelService.create(hotel.toHotelModel())
    }

    @GetMapping("/{id}")
    @Operation(
            description = "Get an item by id."
    )
    fun getHotel(@PathVariable id: Int): HotelResponse {
        return hotelService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            description = "Change an item by id."
    )
    fun update(@PathVariable id: Int, @RequestBody hotel: PutHotelRequest) {
        val hotelSaved = hotelService.findById(id)
        hotelService.update(hotel.toHotelModel(hotelSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            description = "Delete an item by id."
    )
    fun delete(@PathVariable id: Int) {
        hotelService.delete(id)
    }
}