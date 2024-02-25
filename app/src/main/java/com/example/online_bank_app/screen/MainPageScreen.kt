package com.example.online_bank_app.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.online_bank_app.section.BalanceSection
import com.example.online_bank_app.section.CardsSection
import com.example.online_bank_app.section.ToolbarMainSection
import com.example.online_bank_app.section.TransactionsSection
import com.example.online_bank_app.ui.theme.HomeBackground
import com.example.online_bank_app.viewmodel.MainViewModel

@Composable
fun MainPageScreen(viewModel: MainViewModel, navHostController: NavHostController, padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HomeBackground)
            .padding(padding)
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
    ) {
        ToolbarMainSection()
        Spacer(modifier = Modifier.height(24.dp))
        BalanceSection()
        Spacer(modifier = Modifier.height(16.dp))
        CardsSection(viewModel = viewModel, navHostController = navHostController)
        Spacer(modifier = Modifier.height(16.dp))
        TransactionsSection(viewModel = viewModel)
    }
}