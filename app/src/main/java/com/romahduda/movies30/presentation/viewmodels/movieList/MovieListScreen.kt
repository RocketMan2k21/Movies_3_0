package com.romahduda.movies30.presentation.viewmodels.movieList

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.romahduda.movies30.domain.Movie

@Composable
fun MovieScreen(
    navigateToMoviesDetailsScreen: (Int) -> Unit,
    movies: LazyPagingItems<Movie>
) {
    val context = LocalContext.current
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
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 150.dp),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(movies.itemCount) { index ->
                        val movie = movies[index]
                        movie?.let {
                            MovieItem(
                                movie = it,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable { navigateToMoviesDetailsScreen(it.id) }
                            )
                        }
                    }
                    item{
                        if(movies.loadState.append is LoadState.Loading){
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
}

