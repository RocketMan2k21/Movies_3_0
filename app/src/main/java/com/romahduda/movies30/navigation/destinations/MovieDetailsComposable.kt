package com.romahduda.movies30.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.romahduda.movies30.util.Constants.MOVIES_ARGUMENT_KEY
import com.romahduda.movies30.util.Constants.MOVIES_DETAILS_SCREEN

fun NavGraphBuilder.movieDetailsComposable(
    navigateToMovieListScreen: () -> Unit
) {
    composable(
        route = MOVIES_DETAILS_SCREEN,
        arguments = listOf(navArgument(MOVIES_ARGUMENT_KEY){
            type = NavType.IntType
        })
    ){

    }
}