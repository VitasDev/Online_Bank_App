package com.example.online_bank_app.model

import com.google.gson.annotations.SerializedName

data class ResponseCard(
    @SerializedName("cards")
    val cards: List<Card>
)