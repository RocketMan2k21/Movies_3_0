package com.romahduda.movies30.data.api

import com.romahduda.movies30.data.model.MovieDto
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.romahduda.movies30.BuildConfig
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MoviePagingSource(
    private val moviesApi: MoviesApi
) : PagingSource<Int, MovieDto>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = moviesApi.getMoviesByPage(nextPageNumber, BuildConfig.MOVIES_API_KEY)
            return LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = response.page?.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}