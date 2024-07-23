package com.romahduda.movies30.data.model

data class MovieDto (
    val id               : Int,
    val posterPath       : String?,
    val releaseDate      : String,
    val title            : String,
    val voteAverage      : Double
)
