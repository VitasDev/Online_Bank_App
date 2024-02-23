package com.example.online_bank_app.model

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("cardLast4")
    val cardLast4: Int,
    @SerializedName("cardName")
    val cardName: String,
    @SerializedName("cardHolder")
    val cardHolder: CardHolder
)
