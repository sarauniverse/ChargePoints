package com.cariad.stations.views.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cariad.stations.R
import com.cariad.stations.models.data.ChargePoint
import com.cariad.stations.models.data.Connection
import com.cariad.stations.models.data.UsageType
import com.cariad.stations.util.getDisplayAddress
import com.cariad.stations.util.getDisplayDistance
import com.cariad.stations.util.getDisplayName
import com.cariad.stations.views.ui.theme.ChargingStationsTheme

@Composable
fun ChargePointDetails(chargePoint: ChargePoint, onNavigateClick: (Double, Double) -> Unit) {
    ChargingStationsTheme {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(
                    text = chargePoint.getDisplayName()
                )
            })
        }, modifier = Modifier.fillMaxSize()) {
            ChargePointDetailsContainer(chargePoint, modifier = Modifier.padding(it), onNavigateClick = onNavigateClick)
        }
    }
}

@Composable
private fun ChargePointDetailsContainer(chargePoint: ChargePoint, modifier: Modifier, onNavigateClick: (Double, Double) -> Unit) {
    Column(modifier = modifier
        .verticalScroll(rememberScrollState())
        .fillMaxWidth()
        .padding(bottom = 16.dp)) {
        ChargePointSummary(chargePoint = chargePoint, modifier = modifier, onNavigateClick = onNavigateClick)
        ConnectionsDetails(connectionInfoList = chargePoint.connections, modifier = modifier)
        UsageDetail(usageType = chargePoint.usageType, modifier = modifier)
    }
}

@Composable
private fun ChargePointSummary(chargePoint: ChargePoint, modifier: Modifier, onNavigateClick: (Double, Double) -> Unit) {
    Column(horizontalAlignment = Alignment.Start, modifier = modifier.padding(16.dp)) {
        Text(text = chargePoint.getDisplayName(), fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Column {
                Text(text = chargePoint.getDisplayAddress())
                Text(text = chargePoint.getDisplayDistance())
                if(chargePoint.numberOfPoints != null) {
                    Text(text = stringResource(id = R.string.charge_point_available_charge_points, formatArgs = arrayOf(chargePoint.numberOfPoints)))
                }
            }
            Column {
                Button(onClick = {
                    onNavigateClick.invoke(chargePoint.addressInfo.latitude, chargePoint.addressInfo.longitude)
                }, shape = AbsoluteRoundedCornerShape(50)) {
                    Text(text = stringResource(id = R.string.charge_point_item_btn_text_navigate))
                }
            }
        }
    }
    Divider(color = Color.LightGray)
}

@Composable
private fun ConnectionsDetails(connectionInfoList: List<Connection>, modifier: Modifier) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = stringResource(id = R.string.charge_point_conn_title), fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
    connectionInfoList.forEach { 
        ConnectionDetail(connection = it, modifier = modifier)
    }
}

@Composable
private fun ConnectionDetail(connection: Connection, modifier: Modifier) {
    val connectionProperties = mutableMapOf<String,String>()
    if(connection.power != null) {
        connectionProperties[stringResource(id = R.string.charge_point_conn_power)] = "${connection.power} kW"
    }
    if(connection.amps != null) {
        connectionProperties[stringResource(id = R.string.charge_point_conn_current)] = "${connection.amps} amp"
    }
    if(connection.voltage != null) {
        connectionProperties[stringResource(id = R.string.charge_point_conn_voltage)] = "${connection.voltage} V"
    }
    if(connection.currentType != null) {
        connectionProperties[stringResource(id = R.string.charge_point_current_type)] = connection.currentType.title
    }
    connectionProperties[stringResource(id = R.string.charge_point_level_title)] = connection.level.title
    Column(modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)) {
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)) {
            Image(painter = painterResource(id = R.drawable.ic_outline_power_24), modifier = modifier, contentDescription = null)
        }
        Column {
            connectionProperties.forEach {
                Column(modifier = modifier
                    .fillMaxWidth()) {
                    Row(modifier = modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = it.key)
                        Text(text = it.value)
                    }
                }
            }
        }
    }
}

@Composable
private fun UsageDetail(usageType: UsageType?, modifier: Modifier) {
    if(usageType == null) {
        return
    }
    val paymentInfoMap = mutableMapOf<Int, Int>()
    if(usageType.isPayAtLocation == true) {
        paymentInfoMap[R.drawable.ic_baseline_money_24] = R.string.charge_point_payment_pay_at_location
    }
    if(usageType.isMembershipRequired == true) {
        paymentInfoMap[R.drawable.ic_outline_card_membership_24] = R.string.charge_point_payment_membership_required
    }
    if(paymentInfoMap.isEmpty()) {
        return
    }

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = stringResource(id = R.string.charge_point_payment_title), fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }

    paymentInfoMap.forEach {
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)) {
            Row(modifier = modifier.fillMaxWidth()) {
                Image(painter = painterResource(id = it.key), contentDescription = null, modifier.padding(end = 4.dp))
                Text(text = stringResource(id = it.value))
            }
        }
    }
}