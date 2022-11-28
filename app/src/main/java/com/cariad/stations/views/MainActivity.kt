package com.cariad.stations.views

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import com.cariad.stations.R
import com.cariad.stations.models.data.ChargePointsCriteria
import com.cariad.stations.models.data.DistanceUnit
import com.cariad.stations.scheduler.IRepeatedScheduler
import com.cariad.stations.scheduler.IRepeatedSchedulerLifeCycleObserver
import com.cariad.stations.scheduler.RepeatedSchedulerImpl
import com.cariad.stations.scheduler.RepeatedSchedulerLifeCycleObserverImpl
import com.cariad.stations.viewmodels.ChargePointsViewModel
import com.cariad.stations.views.ui.navigation.Navigation
import com.cariad.stations.views.ui.screens.GooglePlayServicesErrorUI
import com.cariad.stations.views.ui.theme.ChargingStationsTheme
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val tag = this.javaClass.name

    private val chargePointsViewModel by viewModels<ChargePointsViewModel>()
    private val chargePointsCriteria = ChargePointsCriteria(
        centreLatitude = 52.526,
        centreLongitude = 13.415,
        maxDistance = 5.0,
        maxDistanceUnit = DistanceUnit.KM
    )

    private val handler = Handler(Looper.getMainLooper())
    private val repeatedScheduler: IRepeatedScheduler = RepeatedSchedulerImpl(handler, 30 * 1000L) {
        Log.i(tag, "Refreshing the data")
        chargePointsViewModel.refreshChargePoints(chargePointsCriteria)
    }
    private val repeatedSchedulerLifeCycleObserver: IRepeatedSchedulerLifeCycleObserver =
        RepeatedSchedulerLifeCycleObserverImpl(repeatedScheduler)

    private val requestCodePlayServices : Int = 1265

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val connectionResultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        if(connectionResultCode != ConnectionResult.SUCCESS) {
            if(GoogleApiAvailability.getInstance().isUserResolvableError(connectionResultCode)) {
                GoogleApiAvailability.getInstance().getErrorDialog(this, connectionResultCode, requestCodePlayServices)
            }
            else {
                setContent {
                    GooglePlayServicesErrorUI {
                        finish()
                    }
                }
            }
        }
        else {
            lifecycle.addObserver(repeatedSchedulerLifeCycleObserver)
            setContent {
                Navigation(chargePointsViewModel = chargePointsViewModel, chargePointsCriteria = chargePointsCriteria)
            }
        }
    }
}