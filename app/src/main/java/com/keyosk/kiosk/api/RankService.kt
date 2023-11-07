package com.keyosk.kiosk.api

import retrofit2.Call
import retrofit2.http.GET

interface RankService {
    @GET("list.json")
    fun getRankData(): Call<List<Rank>>
}
