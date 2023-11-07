package com.keyosk.kiosk.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TouchService {
    @FormUrlEncoded
    @POST("/api/touch")
    fun sendTouchCoordinates(
        @Field("x") x: Float,
        @Field("y") y: Float
    ): Call<Void>
}
