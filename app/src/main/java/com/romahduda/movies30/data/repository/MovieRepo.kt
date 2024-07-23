package com.romahduda.movies30.data.repository

import androidx.paging.PagingData
import com.romahduda.movies30.domain.Movie
import com.romahduda.movies30.domain.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getMovieById(movieId: Int) : Flow<MovieDetails>
    fun getPagingMovieFlow(): Flow<PagingData<Movie>>
}