package com.romahduda.movies30

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.romahduda.movies30.data.api.MoviePagingSource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test


class MovieSourcePagingTest {
    private val movieFactory = MovieFactory()

    private val mockMovies = listOf(
        movieFactory.createMovie(),
        movieFactory.createMovie(),
        movieFactory.createMovie(),
        movieFactory.createMovie(),
    )
    private val fakeApi = FakeMovieApi().apply {
        mockMovies.forEach { movie -> addMovie(movie) }
    }
    private val pagingSource = MoviePagingSource(
        fakeApi
    )

    private val pager = TestPager(PagingConfig(
        pageSize = 20
    ), pagingSource)


    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runTest {
        val pagingSourceLoadResult = pager.refresh() as PagingSource.LoadResult.Page

        assertEquals(mockMovies.size, pagingSourceLoadResult.data.size)
        assertEquals(mockMovies, pagingSourceLoadResult.data)
    }

    @Test
    fun test_consecutive_loads() = runTest {
        val page = with(pager) {
            refresh()
            append()
            append()
        } as PagingSource.LoadResult.Page

        assertEquals(page.data, mockMovies)
    }

    @Test
    fun refresh_returnError(){
        fakeApi.setReturnsError()

        runTest {
            val result = pager.refresh()
            assertTrue(result is PagingSource.LoadResult.Error)
        }
    }

}