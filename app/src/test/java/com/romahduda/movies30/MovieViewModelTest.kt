package com.romahduda.movies30

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.romahduda.movies30.data.repository.MovieRepo
import com.romahduda.movies30.domain.Movie
import com.romahduda.movies30.domain.MovieDetails
import com.romahduda.movies30.presentation.viewmodels.MovieViewModel
import com.romahduda.movies30.util.RequestState
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val coroutineScope =  MainCoroutineRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieRepo: MovieRepo
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        movieRepo = mockk()
        viewModel = MovieViewModel(movieRepo)
    }

    @Test
    fun `moviesPagingFlow emits expected data`() = runTest {
        val mockMovies = listOf(
            Movie(1, "Title 1", "2022", 8.0, "path1"),
            Movie(2, "Title 2", "2023", 7.5, "path2")
        )
        val pagingData = PagingData.from(mockMovies)
        val mockFlow = flowOf(pagingData)

        coEvery { movieRepo.getPagingMovieFlow() } returns mockFlow

        viewModel.moviesPagingFlow.test {
            assertEquals(pagingData, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getMovieById emits success state`() = runTest {
        val movieId = 1
        val movieDetails = MovieDetails(movieId, "Title 1", "2022", "path1", 8.0, "overview", 120, 100000, 500000, "tagline")
        coEvery { movieRepo.getMovieById(movieId) } returns flowOf(movieDetails)

        viewModel.getMovieById(movieId)

        viewModel.movieDetails.test {
            assertEquals(RequestState.Loading, awaitItem())
            assertEquals(RequestState.Success(movieDetails), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getMovieById emits error state`() = runTest {
        val movieId = 1
        val exception = RuntimeException("Something went wrong")
        coEvery { movieRepo.getMovieById(movieId) } returns flow { throw exception }

        viewModel.getMovieById(movieId)

        viewModel.movieDetails.test {
            assertEquals(RequestState.Loading, awaitItem())
            assertEquals(RequestState.Error(exception), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
