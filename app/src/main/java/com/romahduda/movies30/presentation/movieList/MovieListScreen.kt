package com.romahduda.movies30.presentation.movieList

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
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

    val movies = sharedViewModel.moviesPagingFlow.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()){
        if(movies.loadState.refresh is LoadState.Loading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if(movies.loadState.refresh is LoadState.Error) {
            EmptyContent()
        }else{
                GridContent(
                    movies = movies,
                    navigateToMoviesDetailsScreen = navigateToMoviesDetailsScreen
                )
            }
        }
}

@Composable
fun EmptyContent(){
    Box(modifier = Modifier.fillMaxSize()){
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Connection Error. Check Your Network Settings"
        )
    }
}



