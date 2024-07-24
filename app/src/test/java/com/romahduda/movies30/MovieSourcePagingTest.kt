package com.romahduda.movies30

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.romahduda.movies30.data.api.MoviePagingSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals



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
    val pagingSource = MoviePagingSource(
        fakeApi
    )

    val pager = TestPager(PagingConfig(
        pageSize = 20
    ), pagingSource)


    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runTest {


        val result = pager.refresh() as PagingSource.LoadResult.Page

        // assertions against the loaded data
        assertEquals(mockMovies.size, result.data.size)
        assertEquals(mockMovies, result.data)
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

}