package com.romahduda.movies30.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.romahduda.movies30.util.Constants.MOVIES_SCREEN

fun NavGraphBuilder.movieListComposable (
    navigateToMoviesDetailsScreen: (movieId: Int) -> Unit
) {
    composable(
        route = MOVIES_SCREEN
    ){

    }


}