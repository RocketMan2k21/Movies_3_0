package com.romahduda.movies30.data.model

data class MovieDetailsDto (
    val id               : Int,
    val poster_path       : String?,
    val release_date      : String,
    val title            : String,
    val vote_average      : Double,
    val overview: String,
    val runtime: Int,
    val budget: Int,
    val revenue: Int,
    val tagline: String
)