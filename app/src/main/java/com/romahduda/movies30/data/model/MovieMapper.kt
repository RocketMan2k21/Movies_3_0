package com.romahduda.movies30.data.model

import com.romahduda.movies30.domain.Movie
import com.romahduda.movies30.domain.MovieDetails

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        posterPath  = posterPath,
        releaseDate = releaseDate,
        title    = title,
        voteAverage = voteAverage,
    )
}

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        posterPath  = posterPath,
        releaseDate = releaseDate,
        title    = title,
        voteAverage = voteAverage,
        overview = overview,
        runtime = runtime,
        budget = budget,
        revenue = revenue,
        tagline = tagline
    )
}
