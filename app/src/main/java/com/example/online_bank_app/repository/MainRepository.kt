package com.example.online_bank_app.repository

import com.example.online_bank_app.api.ApiService
import com.example.online_bank_app.model.ResponseCard
import com.example.online_bank_app.model.ResponseTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    fun getCards(): Flow<ResponseCard> = flow {
        emit(apiService.getCards())
    }.flowOn(Dispatchers.IO)

    fun getTransactions(): Flow<ResponseTransaction> = flow {
        emit(apiService.getTransactions())
    }.flowOn(Dispatchers.IO)
}