package com.romahduda.movies30.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.romahduda.movies30.data.api.MoviePagingSource
import com.romahduda.movies30.data.api.MoviesApi
import com.romahduda.movies30.data.model.MovieDto
import com.romahduda.movies30.data.repository.MovieRepo
import com.romahduda.movies30.data.repository.MovieRepoImpl
import com.romahduda.movies30.util.Constants.MOVIE_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideRepoImpl(
        moviesApi: MoviesApi,
        pager: Pager<Int, MovieDto>
    ): MovieRepo = MovieRepoImpl(moviesApi, pager)

    @Provides
    @Singleton
    fun provideMoviePager(moviesApi: MoviesApi): Pager<Int, MovieDto> {
        return Pager(
            config = PagingConfig(pageSize = 20)
        ) {
            MoviePagingSource(moviesApi)
        }
    }

}