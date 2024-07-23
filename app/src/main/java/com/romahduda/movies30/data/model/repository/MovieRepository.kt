package com.romahduda.movies30.data.model.repository

import Movie
import com.romahduda.movies30.data.model.MoviesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi : MoviesApi
){

    suspend fun getMoviesByPage(page: Int, apiKey: String) =
        movieApi.getMoviesByPage(page, apiKey)
}