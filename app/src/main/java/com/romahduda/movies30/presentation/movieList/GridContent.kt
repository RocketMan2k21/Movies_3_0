package com.romahduda.movies30.presentation.movieList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.romahduda.movies30.domain.Movie
import com.romahduda.movies30.ui.theme.Movies30Theme
import com.romahduda.movies30.util.Constants.IMAGE_TMDB_BASE_URL

@Composable
fun GridContent(
    movies: LazyPagingItems<Movie>,
    navigateToMoviesDetailsScreen: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = movies.itemCount,
            ) { index ->
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
            if(movies.loadState.append is LoadState.Error){
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Something Went Wrong"
                    )
                }
            }
        }
    }
}


@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation()
    ){
        Column(

        ) {
            AsyncImage(
                model = IMAGE_TMDB_BASE_URL + movie.poster_path,
                contentDescription = movie.title,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.title,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie.release_date!!.split("-")[0], // Only showing the year
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }

}

@Preview
@Composable
fun MovieItemPreview() {
    Movies30Theme {
        MovieItem(
            movie = Movie(
                id = 1,
                poster_path = "Some Movie here",
                release_date = "2024-07-24",
                title = "Be Drunk",
                vote_average = 6.4
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
