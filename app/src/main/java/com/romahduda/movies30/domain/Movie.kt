package com.romahduda.movies30.domain

data class Movie(
    val id               : Int,
    val posterPath       : String?,
    val releaseDate      : String,
    val title            : String,
    val voteAverage      : Double
)