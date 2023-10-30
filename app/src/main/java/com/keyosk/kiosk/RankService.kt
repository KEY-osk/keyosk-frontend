package com.keyosk.kiosk

import retrofit2.Call
import retrofit2.http.GET

interface RankService {
    @GET("your_api_endpoint")
    fun getRankData(): Call<List<Rank>>
}
