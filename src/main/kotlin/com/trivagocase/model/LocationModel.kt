package com.trivagocase.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "location")
class LocationModel (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        @Column
        val city: String,
        @Column
        val state: String,
        @Column
        val country: String,
        @Column
        val zip_code: String,
        @Column
        val address: String
){

}