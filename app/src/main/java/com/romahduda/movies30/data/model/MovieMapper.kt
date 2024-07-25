package com.romahduda.movies30.data.model

import com.romahduda.movies30.domain.Movie
import com.romahduda.movies30.domain.MovieDetails

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title    = title,
        release_date = release_date,
        vote_average = vote_average,
        poster_path  = poster_path
    )
}

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        title    = title,
        releaseDate = release_date,
        posterPath  = poster_path,
        voteAverage = vote_average,
        overview = overview,
        runtime = runtime,
        budget = budget,
        revenue = revenue,
        tagline = tagline
    )
}
