package com.cariad.stations.models.data

data class ChargePointsCriteria(
    val centreLatitude: Double,
    val centreLongitude: Double,
    val maxDistance: Double,
    val maxDistanceUnit: DistanceUnit
)

enum class DistanceUnit(val value: String, val integerVal: Int) {
    KM("km", 2),
    MILES("miles", 1);
}

fun parseDistanceUnit(integerVal: Int): DistanceUnit {
    return when(integerVal) {
        DistanceUnit.KM.integerVal -> DistanceUnit.KM
        DistanceUnit.MILES.integerVal -> DistanceUnit.MILES
        else -> DistanceUnit.KM
    }
}
