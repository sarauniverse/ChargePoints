package com.cariad.stations.api

import okhttp3.Interceptor
import okhttp3.Response

class OCMApiKeyHeaderInterceptor(private val ocmApiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headerBuilder = request.headers.newBuilder()
        headerBuilder.add("X-API-Key", ocmApiKey)
        val newRequest = request.newBuilder()
            .headers(headerBuilder.build())
            .build()
        return chain.proceed(newRequest)
    }
}