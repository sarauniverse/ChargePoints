package com.cariad.stations.util

import java.util.*

fun Double.roundTo(precision: Int): Double {
    return "%.${precision}f".format(this, Locale.ENGLISH).toDouble()
}