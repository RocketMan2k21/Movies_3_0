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
