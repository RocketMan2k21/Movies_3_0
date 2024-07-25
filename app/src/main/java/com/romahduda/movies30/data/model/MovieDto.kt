package com.romahduda.movies30.data.model

data class MovieDto (
    val id               : Int,
    val title            : String,
    val release_date      : String?,
    val vote_average      : Double,
    val poster_path       : String?,
)
