package com.cariad.stations.views.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cariad.stations.models.data.ChargePoint
import com.cariad.stations.models.data.ChargePointsCriteria
import com.cariad.stations.viewmodels.ChargePointsViewModel
import com.cariad.stations.views.ui.screens.ChargePointDetails
import com.cariad.stations.views.ui.screens.ChargePoints
import com.cariad.stations.views.ui.screens.Screen
import com.google.gson.Gson

@Composable
fun Navigation(chargePointsViewModel: ChargePointsViewModel, chargePointsCriteria: ChargePointsCriteria) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ChargePoints.route) {
        composable(route = Screen.ChargePoints.route) {
            ChargePoints(chargePointsViewModel = chargePointsViewModel, chargePointsCriteria = chargePointsCriteria) {
                val chargePointJson = Uri.encode(Gson().toJson(it))
                navController.navigate(Screen.ChargePointDetails.route.substituteParams(mapOf("chargePoint" to chargePointJson)))
            }
        }
        composable(route = Screen.ChargePointDetails.route, arguments = listOf(
            navArgument("chargePoint") {
                type = NavType.StringType
            }
        )) {
            val chargePoint = Gson().fromJson(it.arguments?.getString("chargePoint"), ChargePoint::class.java)
            ChargePointDetails(chargePoint)
        }
    }
}

fun String.substituteParams(paramMap: Map<String, Any>): String {
    var replacedString = this
    paramMap.forEach {
        replacedString = replacedString.replace("{${it.key}}", "${it.value}")
    }
    return replacedString
}