package com.example.online_bank_app.model

import com.google.gson.annotations.SerializedName

data class Merchant(
    @SerializedName("name")
    val name: String? = null
)
