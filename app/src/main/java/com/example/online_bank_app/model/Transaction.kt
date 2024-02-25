package com.example.online_bank_app.model

import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("amount")
    val amount: Float,
    @SerializedName("type")
    val type: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("merchant")
    val merchant: Merchant,
    @SerializedName("card")
    val card: Card? = null,
    @SerializedName("account")
    val account: Account,
    @SerializedName("createDate")
    val createDate: String
)