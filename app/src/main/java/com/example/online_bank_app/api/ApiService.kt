package com.example.online_bank_app.api

import com.example.online_bank_app.model.ResponseCard
import com.example.online_bank_app.model.ResponseTransaction
import retrofit2.http.GET

interface ApiService {
    @GET("/cards")
    suspend fun getCards(): ResponseCard

    @GET("/cards/transactions")
    suspend fun getTransactions(): ResponseTransaction
}