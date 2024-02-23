package com.example.online_bank_app.api

import com.example.online_bank_app.model.ResponseCard

sealed class CardApiState {
    class Success(val data: ResponseCard) : CardApiState()
    class Failure(val message: Throwable) : CardApiState()
    data object Loading : CardApiState()
    data object Empty : CardApiState()
}