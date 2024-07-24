package com.romahduda.movies30

import androidx.compose.ui.modifier.modifierLocalMapOf
import com.romahduda.data.model.MovieResponseDto
import com.romahduda.movies30.data.api.MoviesApi
import com.romahduda.movies30.data.model.MovieDetailsDto
import com.romahduda.movies30.data.model.MovieDto
import java.util.Arrays

class FakeMovieApi: MoviesApi {
    private val movies = arrayListOf<MovieDto>()
    private val movieFactory: MovieFactory = MovieFactory()

    fun addMovie(movie: MovieDto){
        movies.add(movie)
    }
    override suspend fun getMoviesByPage(
        page: Int,
        apiKey: String
    ): MovieResponseDto {
        return MovieResponseDto(
            page = page,
            results = movies,
            totalPages = 1,
            totalResults = 4
        )
    }

    override suspend fun getMovieById(id: Int): MovieDetailsDto {
        TODO("Not yet implemented")
    }
}