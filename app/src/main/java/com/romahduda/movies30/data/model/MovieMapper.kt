package com.romahduda.movies30.data.model

import com.romahduda.movies30.domain.Movie
import com.romahduda.movies30.domain.MovieDetails

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        poster_path  = poster_path,
        release_date = release_date,
        title    = title,
        vote_average = vote_average,
    )
}

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        posterPath  = poster_path,
        releaseDate = release_date,
        title    = title,
        voteAverage = vote_average,
        overview = overview,
        runtime = runtime,
        budget = budget,
        revenue = revenue,
        tagline = tagline
    )
}
