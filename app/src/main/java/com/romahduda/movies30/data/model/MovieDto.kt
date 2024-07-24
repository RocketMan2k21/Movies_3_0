package com.romahduda.movies30.data.model

data class MovieDto (
    val id               : Int,
    val poster_path       : String?,
    val release_date      : String?,
    val title            : String,
    val vote_average      : Double
)
