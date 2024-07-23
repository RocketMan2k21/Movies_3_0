package com.romahduda.movies30.data.model

import com.romahduda.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getMoviesByPage(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ) : MovieResponse
}