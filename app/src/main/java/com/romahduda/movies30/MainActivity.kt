package com.romahduda.movies30

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.romahduda.movies30.navigation.SetupNavigation
import com.romahduda.movies30.presentation.viewmodels.MovieViewModel
import com.romahduda.movies30.ui.theme.Movies30Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel : MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Movies30Theme {
                    navController = rememberNavController()
                    SetupNavigation(
                        navController = navController,
                        movieViewModel = viewModel
                        )
            }
        }
    }
}
