package com.trivagocase.model

import jakarta.persistence.*

@Entity(name = "hotel")
data class HotelModel (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        val name: String,

        @Column
        val rating: Int,

        @Column
        val category: String,

        @OneToOne(cascade = arrayOf(CascadeType.PERSIST))
        @JoinColumn(name = "hotel_id")
        val location: LocationModel? = null,

        @Column
        val image: String,

        @Column
        val reputation: Int,

        @Column
        val price: Double,

        @Column
        var availability: Int
){
        @Column
        var reputationBadge: String = ""
}