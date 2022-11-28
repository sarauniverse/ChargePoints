package com.cariad.stations.di

import com.cariad.stations.BuildConfig
import com.cariad.stations.api.ChargePointsService
import com.cariad.stations.api.OCMApiKeyHeaderInterceptor
import com.cariad.stations.models.repo.ChargePointsRepositoryImpl
import com.cariad.stations.models.repo.IChargePointsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChargePointsModule {

    @Provides
    @Singleton
    fun getChargePointsRepository(chargePointsService: ChargePointsService): IChargePointsRepository {
        return ChargePointsRepositoryImpl(chargePointsService)
    }

    @Provides
    @Singleton
    fun getChargePointService(okHttpClient: OkHttpClient): ChargePointsService {
        return Retrofit.Builder().apply {
            baseUrl("https://api.openchargemap.io")
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build().create(ChargePointsService::class.java)
    }

    @Provides
    @Singleton
    fun getOkHttpClient(@Named("OcmApiKey") ocmApiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(OCMApiKeyHeaderInterceptor(ocmApiKey))
            .build()
    }

    @Provides
    @Singleton
    @Named("OcmApiKey")
    fun getOcmApiKey(): String {
        return BuildConfig.OCM_API_KEY
    }

}