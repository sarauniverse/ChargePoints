package com.cariad.stations.views.ui.screens

import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cariad.stations.R
import com.cariad.stations.models.data.ChargePoint
import com.cariad.stations.models.data.ChargePointsCriteria
import com.cariad.stations.util.getDisplayAddress
import com.cariad.stations.util.getDisplayDistance
import com.cariad.stations.util.getDisplayName
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

        val uiState: State<UIState<List<ChargePoint>>> = remember {
            chargePointsViewModel.getChargePoints(chargePointsCriteria)
        }.collectAsState(initial = UIState.STARTED())

        val chargePoints = when(uiState.value) {
            is UIState.SUCCESS -> (uiState.value as UIState.SUCCESS<List<ChargePoint>>).obj
            else -> arrayListOf()
        }

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
            BottomSheetChargePointsList(
                center = center,
                chargePoints = chargePoints,
                modalBottomSheetState = modalBottomSheetState,
                modifier = Modifier.padding(it),
                onItemInfoClick = onItemInfoClick
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun FloatingButton(modalBottomSheetState: ModalBottomSheetState, onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = {
            if(modalBottomSheetState.isVisible) {
                Text(stringResource(id = R.string.charge_points_floating_btn_text_map))
            } else {
                Text(stringResource(id = R.string.charge_points_floating_btn_text_charge_stations))
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
private fun ChargePointsList(chargePoints: List<ChargePoint>, modifier: Modifier, onItemInfoClick: (ChargePoint) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier
        .padding(top = 24.dp, start = 8.dp, end = 8.dp)
        .fillMaxWidth()
        .defaultMinSize(100.dp)) {
        items(chargePoints) {
            ChargePointItem(chargePoint = it, modifier = modifier, onItemInfoClick)
            Divider(color = Color.LightGray, modifier = Modifier.padding(top = 12.dp, bottom = 4.dp))
        }
    }
}

@Composable
private fun ChargePointItem(chargePoint: ChargePoint, modifier: Modifier, onItemInfoClick: (ChargePoint) -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = chargePoint.getDisplayName(), fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = chargePoint.getDisplayDistance())
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = chargePoint.getDisplayAddress())
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Row (horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { }, shape = AbsoluteRoundedCornerShape(50)) {
                Text(text = stringResource(id = R.string.charge_point_item_btn_text_navigate))
            }
            Button(onClick = { onItemInfoClick.invoke(chargePoint)  }, shape = AbsoluteRoundedCornerShape(50)) {
                Text(text = stringResource(id = R.string.charge_point_item_btn_text_info))
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomSheetChargePointsList(center: LatLng,
                                        chargePoints: List<ChargePoint>,
                                        modalBottomSheetState: ModalBottomSheetState,
                                        modifier: Modifier,
                                        onItemInfoClick: (ChargePoint) -> Unit) {
    ModalBottomSheetLayout(sheetContent = {
        Box(modifier = Modifier.height(1.dp))
        ChargePointsList(chargePoints = chargePoints, modifier = Modifier, onItemInfoClick)
    },
        sheetBackgroundColor = Color.White,
        sheetElevation = 0.dp,
        modifier = modifier,
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStartPercent = 5, topEndPercent = 5),
    )
    {
        ChargePointsMapView(center = center, chargePoints = chargePoints, modifier)
    }
}

@Composable
private fun ChargePointsMapView(center: LatLng, chargePoints: List<ChargePoint>, modifier: Modifier = Modifier) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 10f)
    }
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
    ) {
        if(chargePoints.isEmpty()) {
            return@GoogleMap
        }
        val latLongBoundsBuilder = LatLngBounds.Builder()
        chargePoints.forEach {
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