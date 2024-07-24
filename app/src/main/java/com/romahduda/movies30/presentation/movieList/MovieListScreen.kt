package com.romahduda.movies30.presentation.movieList

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.romahduda.movies30.presentation.viewmodels.MovieViewModel


@Composable
fun MovieScreen(
    navigateToMoviesDetailsScreen: (Int) -> Unit,
    sharedViewModel : MovieViewModel
) {
    val context = LocalContext.current
    val movies = sharedViewModel.moviesPagingFlow.collectAsLazyPagingItems()
    LaunchedEffect(key1 = movies.loadState) {
        if(movies.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (movies.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }

    }
    Box(modifier = Modifier.fillMaxSize()){
        if(movies.loadState.refresh is LoadState.Loading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }else{
                GridContent(
                    movies = movies,
                    navigateToMoviesDetailsScreen = navigateToMoviesDetailsScreen
                )
            }
        }
}


