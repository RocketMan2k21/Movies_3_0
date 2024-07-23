package com.romahduda.movies30.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.romahduda.movies30.data.repository.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

   val moviesPagingFlow = movieRepo
       .getPagingMovieFlow()
       .cachedIn(viewModelScope)

}