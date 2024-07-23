package com.romahduda.movies30.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.romahduda.movies30.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

}