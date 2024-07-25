package com.romahduda.movies30

import com.romahduda.data.model.MovieResponseDto
import com.romahduda.movies30.data.api.MoviesApi
import com.romahduda.movies30.data.model.MovieDetailsDto
import com.romahduda.movies30.data.model.MovieDto
import retrofit2.HttpException
import retrofit2.Response

class FakeMovieApi: MoviesApi {
    private val movies = arrayListOf<MovieDto>()
    private var movieById : MovieDetailsDto? = null
    private var returnsError = false
    fun addMovieById(movie: MovieDetailsDto){
        movieById = movie
    }

    fun addMovie(movie: MovieDto){
        movies.add(movie)
    }

    fun setReturnsError(){
        returnsError = true
    }
    override suspend fun getMoviesByPage(
        page: Int,
        apiKey: String
    ): MovieResponseDto {
         if (!returnsError){
             return MovieResponseDto(
                 page = page,
                 results = movies,
                 totalPages = 1,
                 totalResults = 4
             )
         }else{
             throw HttpException(Response.error<MovieResponseDto>(400, null))
         }
    }

    override suspend fun getMovieById(id: Int, apiKey: String): MovieDetailsDto {
        if (!returnsError){
            return movieById!!
        }else{
            throw HttpException(Response.error<MovieDetailsDto>(400, null))
        }
    }
}