package com.keyosk.kiosk.api

data class ItemData(
    val id: String, val name: String, val price: String, var count: Int,
    val selectedHotIceOption: String, val optSize: String, val optShotPrice: Int
)
