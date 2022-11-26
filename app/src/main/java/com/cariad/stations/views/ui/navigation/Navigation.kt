package com.cariad.stations.views.ui.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.activity
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
            ChargePoints(chargePointsViewModel = chargePointsViewModel,
                chargePointsCriteria = chargePointsCriteria,
                onItemInfoClick = {
                val chargePointJson = Uri.encode(Gson().toJson(it))
                navController.navigate(Screen.ChargePointDetails.route.substituteParams(mapOf("chargePoint" to chargePointJson)))
            }, onNavigateClick = { latitude, longitude ->
                    launchMaps(navController.context, latitude, longitude)
                })
        }
        composable(route = Screen.ChargePointDetails.route, arguments = listOf(
            navArgument("chargePoint") {
                type = NavType.StringType
            }
        )) {
            val chargePoint = Gson().fromJson(it.arguments?.getString("chargePoint"), ChargePoint::class.java)
            ChargePointDetails(chargePoint) { latitude, longitude ->
                launchMaps(navController.context, latitude, longitude)
            }
        }
    }
}

private fun launchMaps(context: Context, latitude: Double, longitude: Double) {
    val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=$latitude,$longitude")).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(mapIntent)
}

fun String.substituteParams(paramMap: Map<String, Any>): String {
    var replacedString = this
    paramMap.forEach {
        replacedString = replacedString.replace("{${it.key}}", "${it.value}")
    }
    return replacedString
}