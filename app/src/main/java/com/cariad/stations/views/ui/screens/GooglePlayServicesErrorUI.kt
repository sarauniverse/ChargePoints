package com.cariad.stations.views.ui.screens

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cariad.stations.R
import com.cariad.stations.views.ui.theme.ChargingStationsTheme

@Composable
fun GooglePlayServicesErrorUI(onOkClick: () -> Unit) {
    ChargingStationsTheme {
        AlertDialog(onDismissRequest = {}, confirmButton = {
            Button(onClick = { onOkClick.invoke() }) {
                Text(text = stringResource(id = R.string.error_dialog_play_services_irrecoverable_btn_close))
            }
        }, title = { Text(text = stringResource(id = R.string.error_dialog_play_services_irrecoverable_title)) },
            text = { Text(text = stringResource(id = R.string.error_dialog_play_services_irrecoverable_text))
        })
    }
}