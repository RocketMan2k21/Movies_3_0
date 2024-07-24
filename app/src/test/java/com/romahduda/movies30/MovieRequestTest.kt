package com.romahduda.movies30

import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class MovieRequestTest {
    private val movieFactory = MovieFactory()
    val movie = movieFactory.createDetailedMovie()
    private val fakeApi = FakeMovieApi().apply {
        addMovieById(movie)
    }

    @Test
    fun getMovieByIdTest() = runTest {

        assertEquals(movie, fakeApi.getMovieById(1, "API_KEY"))



    }






}