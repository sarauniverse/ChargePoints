package com.cariad.stations.api

import com.cariad.stations.models.data.ChargePoint
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ChargePointsService {

    @GET("/v3/poi")
    @Headers("X-API-Key: 1e2cb9c6-a0e9-4a68-bc09-f3c97a6bd8e4")
    suspend fun getChargePoints(
        @Query("distance") distance: Double,
        @Query("distanceunit") distanceUnit: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double): List<ChargePoint>
}