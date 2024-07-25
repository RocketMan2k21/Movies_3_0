package com.romahduda.movies30

import com.romahduda.movies30.data.model.MovieDetailsDto
import com.romahduda.movies30.data.model.MovieDto
import java.util.concurrent.atomic.AtomicInteger

class MovieFactory {
    private val counter = AtomicInteger(0)
    fun createMovie(movieTitle: String) : MovieDto {
        val id = counter.incrementAndGet()
        val movie = MovieDto(
            id = id,
            poster_path = "poster_path $id",
            release_date = "release_date $id",
            title   = movieTitle,
            vote_average  = 10.0
        )
        return movie
    }

    fun createDetailedMovie() : MovieDetailsDto {
        val id = counter.incrementAndGet()
        val movie = MovieDetailsDto(
            id = id,
            poster_path = "poster_path $id",
            release_date = "release_date $id",
            title   = "title $id",
            vote_average  = 10.0,
            overview = "",
            runtime = 1,
            budget = 1,
            revenue = 1,
            tagline = ""

        )
        return movie
    }
}