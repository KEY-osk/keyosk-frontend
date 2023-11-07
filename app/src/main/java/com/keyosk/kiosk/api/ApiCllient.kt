package com.keyosk.kiosk.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://43.200.153.208"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val rankService: RankService by lazy {
        retrofit.create(RankService::class.java)
    }

    val touchService: TouchService by lazy {
        retrofit.create(TouchService::class.java)
    }
}

