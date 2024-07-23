package com.romahduda.movies30.data.api

import com.romahduda.data.model.MovieResponseDto
import com.romahduda.movies30.data.model.MovieDetailsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getMoviesByPage(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ) : MovieResponseDto

    @GET("movie")
    suspend fun getMovieById(@Query ("movie_id") id: Int) : MovieDetailsDto
}