package com.romahduda.movies30.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.romahduda.movies30.data.repository.MovieRepo
import com.romahduda.movies30.domain.Movie
import com.romahduda.movies30.domain.MovieDetails
import com.romahduda.movies30.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<RequestState<MovieDetails>>(RequestState.Idle)
    val movieDetails: StateFlow<RequestState<MovieDetails>> = _movieDetails

    val moviesPagingFlow: Flow<PagingData<Movie>> = movieRepo
        .getPagingMovieFlow()
        .cachedIn(viewModelScope)

    fun getMovieById(movieId: Int) {
        Log.i("MovieViewModel", "getMovieById() invoked...")
        _movieDetails.value = RequestState.Loading
        viewModelScope.launch {
            movieRepo.getMovieById(movieId)
                .catch { e ->
                    _movieDetails.value = RequestState.Error(e)
                }
                .collect {
                    _movieDetails.value = RequestState.Success(it)
                }
        }
    }
}