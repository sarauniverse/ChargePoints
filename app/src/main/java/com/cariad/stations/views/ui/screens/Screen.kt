package com.cariad.stations.views.ui.screens

sealed class Screen(val route: String) {
    object ChargePoints: Screen("chargePoints")
    object ChargePointDetails: Screen("chargePoints/{chargePoint}")
}