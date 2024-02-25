package com.example.online_bank_app.api

import com.example.online_bank_app.model.Transaction

sealed class CardTransactionApiState {
    class Success(val data: List<Transaction>) : CardTransactionApiState()
    class Failure(val message: Throwable) : CardTransactionApiState()
    data object Loading : CardTransactionApiState()
    data object Empty : CardTransactionApiState()
}