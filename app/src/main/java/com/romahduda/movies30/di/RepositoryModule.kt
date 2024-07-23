package com.romahduda.movies30.di

import com.romahduda.movies30.data.api.MoviesApi
import com.romahduda.movies30.data.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(movieApi: MoviesApi): MovieRepository {
        return MovieRepository(movieApi)
    }
}