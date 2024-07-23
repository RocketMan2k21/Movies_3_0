package com.romahduda.movies30.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.romahduda.movies30.data.repository.MovieRepo
import com.romahduda.movies30.domain.Movie
import com.romahduda.movies30.domain.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    val moviesPagingFlow: Flow<PagingData<Movie>> = movieRepo
       .getPagingMovieFlow()
       .cachedIn(viewModelScope)

    fun getMovieById(movieId: Int): Flow<MovieDetails> {
        return movieRepo.getMovieById(movieId)
    }

}