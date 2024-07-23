package com.romahduda.movies30.data.model

import Movie
import kotlinx.coroutines.flow.Flow

interface MovieApiHelper {
    fun getMoviesByPage(page: Int, apiKey: String) : Flow<List<Movie>>
}