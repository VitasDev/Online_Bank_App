package com.example.online_bank_app.model

import com.google.gson.annotations.SerializedName

data class ResponseTransaction(
    @SerializedName("transactions")
    val transactions: List<Transaction>
)