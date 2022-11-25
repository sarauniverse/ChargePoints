package com.cariad.stations.models.repo

import com.cariad.stations.api.ChargePointsService
import com.cariad.stations.models.data.ChargePoint
import com.cariad.stations.models.data.ChargePointsCriteria
import com.cariad.stations.models.data.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChargePointsRepositoryImpl(
    private val chargePointsService: ChargePointsService): IChargePointsRepository {

    override suspend fun getChargePoints(chargePointsCriteria: ChargePointsCriteria): Flow<Result<out List<ChargePoint>>> = flow {
        try {
            val chargePoints = chargePointsService.getChargePoints(
                latitude= chargePointsCriteria.centreLatitude,
                longitude = chargePointsCriteria.centreLongitude,
                distance = chargePointsCriteria.maxDistance,
                distanceUnit = chargePointsCriteria.maxDistanceUnit.value
            ).sortedBy {
                it.addressInfo.distance
            }
            emit(Result.SUCCESS(chargePoints))
        }
        catch(ex: Exception) {
            emit(Result.ERROR("Exception while fetching the charge points", ex))
        }
    }
}