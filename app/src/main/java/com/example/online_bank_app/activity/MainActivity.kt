package com.example.online_bank_app.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.online_bank_app.section.BottomNavigationBar
import com.example.online_bank_app.screen.CardPageScreen
import com.example.online_bank_app.screen.MainPageScreen
import com.example.online_bank_app.ui.theme.HomeBackground
import com.example.online_bank_app.ui.theme.Online_Bank_AppTheme
import com.example.online_bank_app.viewmodel.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Online_Bank_AppTheme {
                SetBarColor(color = HomeBackground)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(mainViewModel)
                }
            }
        }
    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        }
    ) { padding ->
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "Main_Page"){
                composable("Main_Page"){
                    MainPageScreen(viewModel = viewModel, navHostController = navController)
                }
                composable("Card_Page"){
                    CardPageScreen(viewModel = viewModel, navHostController = navController)
                }
            }
    }
}

