package com.example.online_bank_app.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.online_bank_app.section.CardSection
import com.example.online_bank_app.section.FeatureSection
import com.example.online_bank_app.section.ToolbarCardSection
import com.example.online_bank_app.section.TransactionCardSection
import com.example.online_bank_app.ui.theme.HomeBackground
import com.example.online_bank_app.viewmodel.MainViewModel

@Composable
fun CardPageScreen(viewModel: MainViewModel, navHostController: NavHostController, padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HomeBackground)
            .padding(padding)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        ToolbarCardSection(viewModel = viewModel, navHostController = navHostController)
        Spacer(modifier = Modifier.height(20.dp))
        CardSection()
        Spacer(modifier = Modifier.height(16.dp))
        FeatureSection()
        Spacer(modifier = Modifier.height(24.dp))
        TransactionCardSection(viewModel = viewModel)
    }
}
