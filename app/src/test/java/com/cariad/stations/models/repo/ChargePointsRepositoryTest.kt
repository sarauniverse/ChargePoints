package com.cariad.stations.models.repo

import com.cariad.stations.api.ChargePointsService
import com.cariad.stations.models.data.ChargePointsCriteria
import com.cariad.stations.models.data.DistanceUnit
import com.cariad.stations.models.data.Result
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import okio.Buffer
import org.junit.After
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ChargePointsRepositoryTest {
    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val chargePointService = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ChargePointsService::class.java)

    private val chargePointRepository = ChargePointsRepositoryImpl(chargePointService)

    private val chargePointsCriteria = ChargePointsCriteria(
        centreLatitude = 52.526,
        centreLongitude = 13.415,
        maxDistance = 5.0,
        maxDistanceUnit = DistanceUnit.KM
    )

    @After
    fun afterTest() {
        mockWebServer.shutdown()
    }

    @Test
    fun getChargePoints_Success() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
            .setResponseCode(200)
            .setBody(mockValidChargePointsService))
        val result = chargePointRepository.getChargePoints(chargePointsCriteria).toList()

        Assert.assertEquals(2, (result[result.size-1] as Result.SUCCESS).result.size)
    }

    @Test
    fun getChargePoints_AuthError() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(401)
                .setBody(Buffer().write(byteArrayOf())))
        val result = chargePointRepository.getChargePoints(chargePointsCriteria).toList()

        Assert.assertTrue(result[result.size-1] is Result.ERROR)
    }

    @Test
    fun getChargePoints_ServerNotReachable() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
            .setBody(Buffer().write(byteArrayOf()))
            .setSocketPolicy(SocketPolicy.DISCONNECT_AT_START))
        val result = chargePointRepository.getChargePoints(chargePointsCriteria).toList()

        Assert.assertTrue(result[result.size-1] is Result.ERROR)
    }
}