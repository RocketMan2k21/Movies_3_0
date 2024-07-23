package com.romahduda.movies30.data.model

import kotlinx.coroutines.flow.flow

class MovieApiHelperImpl(private val moviesApi: MoviesApi) : MovieApiHelper {
    override fun getMoviesByPage(page : Int, apiKey: String) = flow{
        emit(moviesApi.getMoviesByPage(page, apiKey).results)
    }
}