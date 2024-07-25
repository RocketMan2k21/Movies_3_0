package com.romahduda.movies30

import com.romahduda.movies30.data.api.MoviesApi
import com.romahduda.movies30.data.repository.MovieRepo

object ServiceLocator {

    private var moviesApi: MoviesApi? = null
    private var movieRepository: MovieRepo? = null

    fun provideMoviesApi(): MoviesApi {
        return moviesApi ?: throw IllegalStateException("MoviesApi is not initialized")
    }

    fun provideMovieRepository(): MovieRepo {
        return movieRepository ?: throw IllegalStateException("MovieRepository is not initialized")
    }

    fun initialize(moviesApi: MoviesApi, movieRepository: MovieRepo) {
        ServiceLocator.moviesApi = moviesApi
        ServiceLocator.movieRepository = movieRepository
    }
}


