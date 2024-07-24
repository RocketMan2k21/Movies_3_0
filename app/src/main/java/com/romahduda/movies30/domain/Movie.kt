package com.romahduda.movies30.domain

data class Movie(
    val id               : Int,
    val poster_path       : String?,
    val release_date      : String?,
    val title            : String,
    val vote_average      : Double
)