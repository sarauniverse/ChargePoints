package com.cariad.stations.models.repo

import com.cariad.stations.models.data.ChargePoint
import com.cariad.stations.models.data.Result
import com.cariad.stations.models.data.ChargePointsCriteria
import kotlinx.coroutines.flow.Flow

interface IChargePointsRepository {
    suspend fun getChargePoints(chargePointsCriteria: ChargePointsCriteria): Flow<Result<out List<ChargePoint>>>
}