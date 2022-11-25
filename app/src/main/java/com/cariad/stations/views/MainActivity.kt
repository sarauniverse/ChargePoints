package com.cariad.stations.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.cariad.stations.models.data.ChargePointsCriteria
import com.cariad.stations.models.data.DistanceUnit
import com.cariad.stations.viewmodels.ChargePointsViewModel
import com.cariad.stations.views.ui.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val chargePointsViewModel by viewModels<ChargePointsViewModel>()
    private val chargePointsCriteria = ChargePointsCriteria(
        centreLatitude = 52.526,
        centreLongitude = 13.415,
        maxDistance = 5.0,
        maxDistanceUnit = DistanceUnit.KM
    )

    private val handler = Handler(Looper.getMainLooper())
    private val handlerLifeCycleObserver =  HandlerLifeCycleObserver(handler, 30*1000L) {
        chargePointsViewModel.refreshChargePoints(chargePointsCriteria)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(handlerLifeCycleObserver)
        setContent {
            Navigation(chargePointsViewModel = chargePointsViewModel, chargePointsCriteria = chargePointsCriteria)
        }
    }
}