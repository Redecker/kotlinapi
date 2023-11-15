package com.trivagocase.repository

import com.trivagocase.model.HotelModel
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface HotelRepository : CrudRepository<HotelModel, Int> {

    @Query("SELECT h FROM hotel h " +
            "JOIN h.location l " +
            "WHERE (:name IS NULL OR h.name LIKE %:name%) " +
            "AND (:rating IS NULL OR h.rating = :rating) " +
            "AND (:reputationBadge IS NULL OR h.reputationBadge = :reputationBadge) " +
            "AND (:city IS NULL OR l.city = :city)")
    @Cacheable("all")
    fun getHotels(
            @Param("name") name: String?,
            @Param("rating") rating: Int?,
            @Param("reputationBadge") reputationBadge: String?,
            @Param("city") city: String?
    ): List<HotelModel>

    @Cacheable("name")
    fun findByNameContaining(
            name: String?
    ): List<HotelModel>

    @Cacheable("rating")
    fun findByRating(
            rating: Int?
    ): List<HotelModel>

    @Cacheable("reputationBadge")
    fun findByReputationBadge(
            reputationBadge: String?
    ): List<HotelModel>

    @Cacheable("locationCity")
    fun findByLocationCity(
            city: String?
    ): List<HotelModel>
}