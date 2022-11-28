package com.cariad.stations.viewmodels.states

sealed class UIState<T> {
    class STARTED<T>: UIState<T>()
    data class REFRESHING<T>(val existingData: T): UIState<T>()
    data class SUCCESS<T>(val obj: T): UIState<T>()
    data class ERROR<T>(val errorMessage: String, val exception: Exception?): UIState<T>()
}