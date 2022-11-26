package com.cariad.stations.util

import com.cariad.stations.models.data.ChargePoint
import com.cariad.stations.models.data.parseDistanceUnit
import java.util.*

fun Double.roundTo(precision: Int): Double {
    return "%.${precision}f".format(this, Locale.ENGLISH).toDouble()
}

fun ChargePoint.getDisplayName(): String {
    return operatorInfo?.title ?: addressInfo.title
}

fun ChargePoint.getDisplayAddress(): String {
    return StringBuilder().apply {
        append(addressInfo.addressLine1)
        append("\n")
        if(addressInfo.addressLine2 != null) {
            append(addressInfo.addressLine2)
            append("\n")
        }
        append(addressInfo.postCode)
        append(" ")
        if(addressInfo.town != null) {
            append(addressInfo.town)
        }
        else {
            append(addressInfo.stateOrProvince)
        }
    }.toString()
}

fun ChargePoint.getDisplayDistance(): String {
    return "${addressInfo.distance.roundTo(1)} ${parseDistanceUnit(addressInfo.distanceUnit).value}"
}