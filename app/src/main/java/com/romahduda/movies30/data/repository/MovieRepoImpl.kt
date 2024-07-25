package com.romahduda.movies30.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.map
import com.romahduda.movies30.BuildConfig
import com.romahduda.movies30.data.api.MoviesApi
import com.romahduda.movies30.data.model.MovieDetailsDto
import com.romahduda.movies30.data.model.MovieDto
import com.romahduda.movies30.data.model.toMovie
import com.romahduda.movies30.data.model.toMovieDetails
import com.romahduda.movies30.domain.MovieDetails
import com.romahduda.movies30.util.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val pager: Pager<Int, MovieDto>
): MovieRepo {

    override fun getPagingMovieFlow() = pager
        .flow
        .map { pager ->
            pager.map {
                it.toMovie()
            }
        }

    override fun getMovieById(movieId: Int) = flow {
        emit(
            moviesApi
                .getMovieById(movieId, BuildConfig.MOVIES_API_KEY)
                .toMovieDetails()
        )
    }
}