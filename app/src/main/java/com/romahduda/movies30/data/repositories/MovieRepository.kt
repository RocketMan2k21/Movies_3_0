package com.romahduda.movies30.data.repositories

import com.romahduda.movies30.data.api.MoviesApi
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi : MoviesApi
){

    suspend fun getMoviesByPage(page: Int, apiKey: String) =
        movieApi.getMoviesByPage(page, apiKey)
}