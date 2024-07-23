package com.romahduda.movies30.domain

data class MovieDetails (
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
