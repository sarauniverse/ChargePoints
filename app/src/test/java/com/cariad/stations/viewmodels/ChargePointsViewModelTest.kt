package com.cariad.stations.viewmodels

import com.cariad.stations.models.data.ChargePointsCriteria
import com.cariad.stations.models.data.DistanceUnit
import com.cariad.stations.models.data.Result
import com.cariad.stations.models.repo.IChargePointsRepository
import com.cariad.stations.viewmodels.states.UIState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChargePointsViewModelTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Mock
    private lateinit var chargePointRepository: IChargePointsRepository

    private val chargePointsCriteria = ChargePointsCriteria(
        centreLatitude = 52.526,
        centreLongitude = 13.415,
        maxDistance = 5.0,
        maxDistanceUnit = DistanceUnit.KM
    )

    @Test
    fun getChargePoints_Success() = runBlocking {
        chargePointRepository.stub {
            onBlocking { getChargePoints(any()) }.doReturn(flow {
                emit(Result.SUCCESS(
                    arrayListOf()
                ))
            })
        }
        val chargePointsViewModel = ChargePointsViewModel(chargePointRepository)
        launch(Dispatchers.Main) {
            val uiState = chargePointsViewModel.getChargePoints(chargePointsCriteria)
            Assert.assertTrue(uiState.value is UIState.STARTED)
            delay(2000)
            Assert.assertTrue(uiState.value is UIState.SUCCESS)
        }.join()
    }

    @Test
    fun getChargePoints_Success_Refreshing() = runBlocking {
        chargePointRepository.stub {
            onBlocking { getChargePoints(any()) }.doReturn(flow {
                emit(Result.SUCCESS(
                    arrayListOf()
                ))
            })
        }
        val chargePointsViewModel = ChargePointsViewModel(chargePointRepository)
        launch(Dispatchers.Main) {
            val uiState = chargePointsViewModel.getChargePoints(chargePointsCriteria)
            Assert.assertTrue(uiState.value is UIState.STARTED)
            delay(2000)
            Assert.assertTrue(uiState.value is UIState.SUCCESS)

            chargePointsViewModel.refreshChargePoints(chargePointsCriteria)
            Assert.assertTrue(uiState.value is UIState.REFRESHING)
            delay(2000)
            Assert.assertTrue(uiState.value is UIState.SUCCESS)
        }.join()
    }

    @Test
    fun getChargePoints_Failure() = runBlocking {
        chargePointRepository.stub {
            onBlocking { getChargePoints(any()) }.doReturn(flow {
                emit(Result.ERROR("", null))
            })
        }
        val chargePointsViewModel = ChargePointsViewModel(chargePointRepository)
        launch(Dispatchers.Main) {
            val uiState = chargePointsViewModel.getChargePoints(chargePointsCriteria)
            Assert.assertTrue(uiState.value is UIState.STARTED)
            delay(2000)
            Assert.assertTrue(uiState.value is UIState.ERROR)
        }.join()
    }

    @Test
    fun getChargePoints_Failure_Refreshing() = runBlocking {
        chargePointRepository.stub {
            onBlocking { getChargePoints(any()) }.doReturn(flow {
                emit(Result.ERROR("", null))
            })
        }
        val chargePointsViewModel = ChargePointsViewModel(chargePointRepository)
        launch(Dispatchers.Main) {
            val uiState = chargePointsViewModel.getChargePoints(chargePointsCriteria)
            Assert.assertTrue(uiState.value is UIState.STARTED)
            delay(2000)
            Assert.assertTrue(uiState.value is UIState.ERROR)

            chargePointsViewModel.refreshChargePoints(chargePointsCriteria)
            Assert.assertTrue(uiState.value is UIState.REFRESHING)
            delay(2000)
            Assert.assertTrue(uiState.value is UIState.ERROR)
        }.join()
    }
}