package com.trivagocase.controller

import com.trivagocase.controller.request.PostHotelRequest
import com.trivagocase.controller.request.PutHotelRequest
import com.trivagocase.controller.response.HotelResponse
import com.trivagocase.extension.toHotelModel
import com.trivagocase.extension.toResponse
import com.trivagocase.model.HotelModel
import com.trivagocase.service.HotelService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("hotels")
class HotelController (
    val hotelService : HotelService
){
    @GetMapping
    fun getAllHotels(@RequestParam name: String?,
               @RequestParam rating: Int?,
               @RequestParam city: String?,
               @RequestParam reputationBadge: String?
               ): List<HotelResponse> {
        return hotelService.getAllHotels(name, rating, reputationBadge, city).map{ it.toResponse()}
    }

    @PutMapping("/{id}/availability")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun reducesAvailability(@PathVariable id: Int) {
        hotelService.reduceAvailability(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody hotel: PostHotelRequest) {
        hotelService.create(hotel.toHotelModel())
    }

    @GetMapping("/{id}")
    fun getHotel(@PathVariable id: Int): HotelResponse {
        return hotelService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody hotel: PutHotelRequest) {
        val hotelSaved = hotelService.findById(id)
        hotelService.update(hotel.toHotelModel(hotelSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        hotelService.delete(id)
    }
}