package com.romahduda.movies30.data.api

import com.romahduda.movies30.data.model.MovieDto
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.romahduda.movies30.BuildConfig
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val moviesApi: MoviesApi
) : RemoteMediator<Int, MovieDto>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieDto>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){ // defines current loadType(page)
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> { // calculate the current page
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null){ // if there is not last item then it must be the first call
                        1
                    } else{
                        (lastItem.id.div(state.config.pageSize)) + 1
                    }
                }
            }

            val movies = moviesApi.getMoviesByPage(
                page = loadKey,
                apiKey = BuildConfig.MOVIES_API_KEY
            )

            MediatorResult.Success(
                endOfPaginationReached = movies.results.isEmpty() // there is the end of pagination
                                                                    // if the list is empty
            )
        }catch (e: IOException){
            MediatorResult.Error(e)
        }catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}