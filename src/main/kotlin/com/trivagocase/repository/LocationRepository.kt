package com.trivagocase.repository

import com.trivagocase.model.LocationModel
import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepository : JpaRepository<LocationModel, Int> {
}