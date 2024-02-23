package com.example.online_bank_app.api

import com.example.online_bank_app.model.ResponseTransaction

sealed class TransactionApiState {
    class Success(val data: ResponseTransaction) : TransactionApiState()
    class Failure(val message: Throwable) : TransactionApiState()
    data object Loading : TransactionApiState()
    data object Empty : TransactionApiState()
}