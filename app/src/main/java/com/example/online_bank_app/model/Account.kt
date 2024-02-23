package com.example.online_bank_app.model

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("accountLast4")
    val accountLast4: Int
)
