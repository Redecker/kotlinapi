package com.trivagocase.service

import com.trivagocase.common.isValidUrl
import com.trivagocase.enums.Errors
import com.trivagocase.exception.BadRequestException
import com.trivagocase.exception.NotFoundException
import com.trivagocase.model.HotelModel
import com.trivagocase.repository.HotelRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.BadRequest
import java.lang.Exception

@Service
class HotelService(
        val hotelRepository: HotelRepository
) {

    fun getAll(name: String?, rating: Int?, reputationBadge: String?, city: String?): List<HotelModel> {
        return hotelRepository.getHotels(name, rating, reputationBadge, city)
    }

    fun create(hotel: HotelModel) {
        val newHotel = hotelValidations(hotel)
        hotelRepository.save(newHotel)
    }

    fun findById(id: Int): HotelModel {
        return hotelRepository.findById(id).orElseThrow{NotFoundException(Errors.HOTELNOTFOUND.message.format(id), Errors.HOTELNOTFOUND.code)}
    }

    fun update(hotel: HotelModel) {
        val updateHotel = hotelValidations(hotel)
        hotelRepository.save(updateHotel)
    }

    fun delete(id: Int) {
        if(!hotelRepository.existsById(id)){
            throw NotFoundException(Errors.HOTELNOTFOUND.message.format(id), Errors.HOTELNOTFOUND.code)
        }
        hotelRepository.deleteById(id)
    }

    fun reduceAvailability(id: Int){
        val updateHotel = findById(id)

        if (updateHotel.availability == 0){
            throw NotFoundException(Errors.HOTELAVAILABILITY.message.format(id), Errors.HOTELAVAILABILITY.code)
        }
        updateHotel.availability--;
        hotelRepository.save(updateHotel)
    }

    fun hotelValidations(hotel: HotelModel): HotelModel{
        val nameWordsRegex = Regex("""\b(?:example|offer|book|website)\b""", RegexOption.IGNORE_CASE)
        if (nameWordsRegex.containsMatchIn(hotel.name) || hotel.name.length <= 10){
            throw BadRequestException(Errors.HOTELNAME.message, Errors.HOTELNAME.code)
        }

        if (hotel.rating !in 0..5 ){
            throw BadRequestException(Errors.HOTELRATING.message, Errors.HOTELRATING.code)
        }

        val categoryWordsRegex = Regex("""\b(?:HOTEL|ALTERNATIVE|HOSTEL|LADGE|RESORTE|GUEST-HOUSE)\b""", RegexOption.IGNORE_CASE)
        if (categoryWordsRegex.equals(hotel.category)){
            throw BadRequestException(Errors.HOTELCATEGORY.message, Errors.HOTELCATEGORY.code)
        }

        if (hotel.location?.zip_code?.length != 5){
            throw BadRequestException(Errors.LOCATIONZIPCODE.message, Errors.LOCATIONZIPCODE.code)
        }

        if (!isValidUrl(hotel.image)){
            throw BadRequestException(Errors.HOTELIMAGE.message, Errors.HOTELIMAGE.code)
        }

        if (hotel.reputation !in 0..1000 ){
            throw BadRequestException(Errors.HOTELREPUTATION.message, Errors.HOTELREPUTATION.code)
        }

        hotel.reputationBadge = when (hotel.reputation){
            in 0..500 -> "red"
            in 501..799 -> "yellow"
            else -> "green"
        }
        return hotel
    }
}