package com.skycom.weatherapp.core.network

import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = "25fb6e612074a99e767de0c0b4b26fc0"
private const val API_KEY_PARAM = "appid"

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter(API_KEY_PARAM, API_KEY)
            .build()

        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}