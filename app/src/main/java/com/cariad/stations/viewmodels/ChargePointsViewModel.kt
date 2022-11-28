package com.cariad.stations.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cariad.stations.models.data.ChargePoint
import com.cariad.stations.models.data.ChargePointsCriteria
import com.cariad.stations.models.data.Result
import com.cariad.stations.models.repo.IChargePointsRepository
import com.cariad.stations.viewmodels.states.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChargePointsViewModel @Inject constructor(
    private val chargePointsRepository: IChargePointsRepository): ViewModel() {

    private val chargePoints = MutableStateFlow<UIState<List<ChargePoint>>>(UIState.STARTED())

    fun getChargePoints(chargePointsCriteria: ChargePointsCriteria): StateFlow<UIState<List<ChargePoint>>> {
        chargePoints.value = UIState.STARTED()
        updateChargePoints(chargePointsCriteria)
        return chargePoints
    }

    fun refreshChargePoints(chargePointsCriteria: ChargePointsCriteria) {
        chargePoints.value = when(chargePoints.value) {
            is UIState.SUCCESS ->  {
                UIState.REFRESHING((chargePoints.value as UIState.SUCCESS<List<ChargePoint>>).obj)
            }
            else -> UIState.REFRESHING(listOf())
        }
        updateChargePoints(chargePointsCriteria)
    }

    private fun updateChargePoints(chargePointsCriteria: ChargePointsCriteria) {
        viewModelScope.launch {
            chargePointsRepository.getChargePoints(chargePointsCriteria).collectLatest {
                chargePoints.value = when(it) {
                    is Result.SUCCESS -> UIState.SUCCESS(it.result)
                    is Result.ERROR -> UIState.ERROR(it.errorMessage, it.exception)
                }
            }
        }
    }
}