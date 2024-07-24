package com.romahduda.movies30.presentation.viewmodels.movieList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.romahduda.movies30.domain.Movie
import com.romahduda.movies30.ui.theme.Movies30Theme
import com.romahduda.movies30.util.Constants.IMAGE_TMDB_BASE_URL

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
            modifier = Modifier.padding(8.dp)
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
