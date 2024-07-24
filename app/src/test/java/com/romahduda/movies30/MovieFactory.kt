package com.romahduda.movies30

import com.romahduda.movies30.data.model.MovieDto
import java.util.concurrent.atomic.AtomicInteger

class MovieFactory {
    private val counter = AtomicInteger(0)
    fun createMovie() : MovieDto {
        val id = counter.incrementAndGet()
        val movie = MovieDto(
            id = id,
            poster_path = "poster_path $id",
            release_date = "release_date $id",
            title   = "title $id",
            vote_average  = 10.0
        )
        return movie
    }
}