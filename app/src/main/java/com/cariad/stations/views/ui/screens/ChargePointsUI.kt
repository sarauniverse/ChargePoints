package com.cariad.stations.views.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cariad.stations.models.data.ChargePoint
import com.cariad.stations.models.data.ChargePointsCriteria
import com.cariad.stations.models.data.parseDistanceUnit
import com.cariad.stations.util.roundTo
import com.cariad.stations.viewmodels.ChargePointsViewModel
import com.cariad.stations.viewmodels.states.UIState
import com.cariad.stations.views.ui.theme.ChargingStationsTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChargePoints(chargePointsViewModel: ChargePointsViewModel, chargePointsCriteria: ChargePointsCriteria, onItemInfoClick: (ChargePoint) -> Unit) {
    ChargingStationsTheme {
        val center = remember {
            LatLng(chargePointsCriteria.centreLatitude, chargePointsCriteria.centreLongitude)
        }
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(center, 10f)
        }

        val uiState: State<UIState<List<ChargePoint>>> = remember {
            chargePointsViewModel.getChargePoints(chargePointsCriteria)
        }.collectAsState(initial = UIState.STARTED())

        val modalBottomSheetState =
            rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true)
        val mainScaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Scaffold(floatingActionButton = {
            FloatingButton(onClick = {
                scope.launch {
                    if(modalBottomSheetState.isVisible) {
                        modalBottomSheetState.hide()
                    }
                    else {
                        modalBottomSheetState.show()
                    }
                }
            }, modalBottomSheetState = modalBottomSheetState)
        },
            scaffoldState = mainScaffoldState) {
            ModalBottomSheetLayout(sheetContent = {
                Box(modifier = Modifier.height(1.dp))
                when(uiState.value) {
                    is UIState.SUCCESS<List<ChargePoint>> -> {
                        ChargePointsList(chargePoints = (uiState.value as UIState.SUCCESS<List<ChargePoint>>).obj, modifier = Modifier, onItemInfoClick)
                    }
                    is UIState.STARTED -> {
                        ChargePointsList(chargePoints = listOf(), modifier = Modifier, onItemInfoClick)
                    }
                }
            },
                sheetBackgroundColor = Color.White,
                sheetElevation = 0.dp,
                modifier = Modifier.padding(it),
                sheetState = modalBottomSheetState,
                sheetShape = RoundedCornerShape(topStartPercent = 5, topEndPercent = 5),
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = MapUiSettings(zoomControlsEnabled = false),
                ) {
                    val latLongBoundsBuilder = LatLngBounds.Builder()
                    when(uiState.value) {
                        is UIState.SUCCESS<List<ChargePoint>> -> {
                            (uiState.value as UIState.SUCCESS<List<ChargePoint>>).obj.forEach {
                                val markerPosition = LatLng(it.addressInfo.latitude, it.addressInfo.longitude)
                                latLongBoundsBuilder.include(markerPosition)
                                Marker(
                                    state = MarkerState(position = markerPosition),
                                    title = it.addressInfo.title,
                                    snippet = it.addressInfo.title
                                )
                            }
                            val cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLongBoundsBuilder.build(), 5)
                            cameraPositionState.move(cameraUpdate)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FloatingButton(modalBottomSheetState: ModalBottomSheetState, onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = {
            if(modalBottomSheetState.isVisible) {
                Text("Map")
            } else {
                Text("Gas stations")
            }
        },
        icon = { Icon(Icons.Filled.List,"") },
        onClick = {
            onClick.invoke()
        },
        shape = AbsoluteRoundedCornerShape(50),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
    )
}

@Composable
fun ChargePointsList(chargePoints: List<ChargePoint>, modifier: Modifier, onItemInfoClick: (ChargePoint) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier.padding(top = 24.dp, start = 8.dp, end = 8.dp).fillMaxWidth().defaultMinSize(100.dp)) {
        items(chargePoints) {
            ChargePointItem(chargePoint = it, modifier = modifier, onItemInfoClick)
            Divider(color = Color.LightGray, modifier = Modifier.padding(top = 12.dp, bottom = 4.dp))
        }
    }
}

@Composable
fun ChargePointItem(chargePoint: ChargePoint, modifier: Modifier, onItemInfoClick: (ChargePoint) -> Unit) {
    val title = chargePoint.operatorInfo?.title ?: chargePoint.addressInfo.title ?: "Gas station"
    title.let { title ->
        Column(modifier = modifier.fillMaxWidth()) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = "${chargePoint.addressInfo.distance.roundTo(1)} ${parseDistanceUnit(chargePoint.addressInfo.distanceUnit).value}")
    }
    Column(modifier = modifier.fillMaxWidth()) {
        val address = StringBuilder().apply {
            append(chargePoint.addressInfo.addressLine1)
            append("\n")
            if(chargePoint.addressInfo.addressLine2 != null) {
                append(chargePoint.addressInfo.addressLine2)
                append("\n")
            }
            append(chargePoint.addressInfo.postCode)
            append(" ")
            if(chargePoint.addressInfo.town != null) {
                append(chargePoint.addressInfo.town)
            }
            else {
                append(chargePoint.addressInfo.stateOrProvince)
            }
        }.toString()
        Text(text = address)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row (horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { }, shape = AbsoluteRoundedCornerShape(50)) {
                Text(text = "Navigate")
            }
            Button(onClick = { onItemInfoClick.invoke(chargePoint)  }, shape = AbsoluteRoundedCornerShape(50)) {
                Text(text = "Info")
            }
        }
    }

}