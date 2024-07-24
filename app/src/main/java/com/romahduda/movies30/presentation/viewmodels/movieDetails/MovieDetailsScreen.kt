package com.romahduda.movies30.presentation.viewmodels.movieDetails

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.romahduda.movies30.domain.MovieDetails
import com.romahduda.movies30.util.Constants.IMAGE_TMDB_BASE_URL
import com.romahduda.movies30.util.RequestState

@Composable
fun MovieDetailsScreen(
    navigateToMovieListScreen: () -> Unit,
    movie: RequestState<MovieDetails>
){
    Box(modifier = Modifier.fillMaxSize()) {
        if (movie is RequestState.Success) {
            Log.i("MovieDetailsScreen", "Selected Movie: $movie")
            DisplayMovie(movie.data)
        }
        if (movie is RequestState.Error) {
            Toast.makeText(LocalContext.current, "Error loading movie data!", Toast.LENGTH_LONG)
                .show()
        }
        if (movie is RequestState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }


}

@Composable
private fun DisplayMovie(movie: MovieDetails) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = IMAGE_TMDB_BASE_URL + movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = movie.title,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = movie.tagline,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Rating: ${movie.voteAverage}",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "${movie.releaseDate}",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Budget: \$${movie.budget}",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Runtime: ${movie.runtime} minutes",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Revenue: \$${movie.revenue}",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = movie.overview,
                modifier = Modifier.padding(bottom = 8.dp)
            )


        }

}

@Preview
@Composable
fun PreviewMovieDetails(){
    MovieDetailsScreen(
        navigateToMovieListScreen = {},
        movie = RequestState.Success(MovieDetails(
            id = 1,
            posterPath = "poster path",
            releaseDate = "2024-24-24",
            title = "Bad Boys",
            voteAverage = 10.0,
            overview = "Some cool movie",
            runtime = 999,
            budget = 99999,
            revenue = 888888,
            tagline = "Bad boys, bad boys"
        )
        )
    )
}