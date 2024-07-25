package com.romahduda.movies30

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import androidx.paging.testing.TestPager
import com.romahduda.movies30.data.model.MovieDto
import com.romahduda.movies30.data.model.toMovie
import com.romahduda.movies30.data.model.toMovieDetails
import com.romahduda.movies30.data.repository.MovieRepoImpl
import com.romahduda.movies30.domain.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

class MovieRepoImplTest {

    @get:Rule
    val coroutineScope =  MainCoroutineRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val movieFactory = MovieFactory()

    private val mockMovies = listOf(
        movieFactory.createMovie(),
        movieFactory.createMovie(),
        movieFactory.createMovie(),
        movieFactory.createMovie()
    )

    private val mockMovieDetails = movieFactory.createDetailedMovie()

    private lateinit var fakeApi: FakeMovieApi
    private lateinit var movieRepo: MovieRepoImpl

    private lateinit var pagingSource: PagingSource<Int, MovieDto>

    private lateinit var pager : Pager<Int, MovieDto>

    @Before
    fun setup() {
        fakeApi = FakeMovieApi().apply {
            mockMovies.forEach { movie -> addMovie(movie) }
            addMovieById(mockMovieDetails)
        }

        pagingSource = FakeMoviePagingSource(mockMovies)

        pager = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {pagingSource}
        )

        movieRepo = MovieRepoImpl(fakeApi, pager)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getPagingMovieFlow_success() = runTest {
        val movies = movieRepo.getPagingMovieFlow().toList()
        advanceUntilIdle()
        assertEquals(mockMovies.size, movies.size)
        assertEquals(mockMovies.map { it.toMovie() }, movies)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getPagingMovieFlow_error() = runTest {

        pagingSource = FakeMoviePagingSource(mockMovies, true)
        val pager = TestPager(PagingConfig(pageSize = 20), pagingSource)

        val result = pager.refresh()

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMovieById_success() = runTest {
        val movieDetails = movieRepo.getMovieById(mockMovieDetails.id).first()
        assertEquals(mockMovieDetails.toMovieDetails(), movieDetails)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMovieById_error() = runTest {
        fakeApi.setReturnsError()

        try {
            movieRepo.getMovieById(mockMovieDetails.id).collect()
            fail("Expected an HttpException to be thrown")
        } catch (e: HttpException) {
            // Expected exception
        }
    }
}

