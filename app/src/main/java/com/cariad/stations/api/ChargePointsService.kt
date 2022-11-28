package com.cariad.stations.api

import com.cariad.stations.models.data.ChargePoint
import retrofit2.http.GET
import retrofit2.http.Query

interface ChargePointsService {

    @GET("/v3/poi")
    suspend fun getChargePoints(
        @Query("distance") distance: Double,
        @Query("distanceunit") distanceUnit: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double): List<ChargePoint>
}