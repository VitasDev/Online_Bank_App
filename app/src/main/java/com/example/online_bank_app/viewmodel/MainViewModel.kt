package com.example.online_bank_app.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.online_bank_app.api.CardApiState
import com.example.online_bank_app.repository.MainRepository
import com.example.online_bank_app.api.TransactionApiState
import com.example.online_bank_app.model.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    val responseCard: MutableState<CardApiState> = mutableStateOf(CardApiState.Empty)
    val responseTransaction: MutableState<TransactionApiState> = mutableStateOf(TransactionApiState.Empty)

    private val _dataCard: MutableState<Card?> = mutableStateOf(null)
    var dataCard: State<Card?> = _dataCard
        private set

    init {
        getCards()
        getTransactions()
    }

    fun setDataCard(card: Card) {
        _dataCard.value = card
    }

    private fun getCards() =
        viewModelScope.launch {
            mainRepository.getCards().onStart {
                responseCard.value = CardApiState.Loading
            }.catch {
                responseCard.value = CardApiState.Failure(it)
            }.collect {
                responseCard.value = CardApiState.Success(it)
            }
        }

    private fun getTransactions() =
        viewModelScope.launch {
            mainRepository.getTransactions().onStart {
                responseTransaction.value = TransactionApiState.Loading
            }.catch {
                responseTransaction.value = TransactionApiState.Failure(it)
            }.collect {
                responseTransaction.value = TransactionApiState.Success(it)
            }
        }
}