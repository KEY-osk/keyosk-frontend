package com.keyosk.kiosk.api

import com.google.gson.annotations.SerializedName

data class Rank(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("category") val category: String,
    @SerializedName("imageUrl") val imageUrl: String
)

