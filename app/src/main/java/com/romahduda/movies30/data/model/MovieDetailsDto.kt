package com.romahduda.movies30.data.model

data class MovieDetailsDto (
    val id               : Int,
    val posterPath       : String?,
    val releaseDate      : String,
    val title            : String,
    val voteAverage      : Double,
    val overview: String,
    val runtime: Int,
    val budget: Int,
    val revenue: Int,
    val tagline: String
)