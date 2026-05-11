package com.qwin.moviematch.feature.movies.data

import com.qwin.moviematch.feature.movies.domain.model.Movie
import com.qwin.moviematch.feature.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeMovieRepository @Inject constructor() : MovieRepository {

    override fun getMovies(): Flow<List<Movie>> = flowOf(fakeMovies)

    override suspend fun getMovieById(id: Int): Movie? {
        return fakeMovies.find { it.id == id }
    }

    private val fakeMovies = listOf(
        Movie(
            id = 1,
            title = "Inception",
            overview = "A thief who steals corporate secrets through dream-sharing technology.",
            posterPath = null,
            voteAverage = 8.8,
            releaseDate = "2010-07-16",
        ),
        Movie(
            id = 2,
            title = "Interstellar",
            overview = "A team of explorers travel through a wormhole in space.",
            posterPath = null,
            voteAverage = 8.6,
            releaseDate = "2014-11-07",
        ),
        Movie(
            id = 3,
            title = "The Dark Knight",
            overview = "Batman faces the Joker, a criminal mastermind who wants to plunge Gotham into anarchy.",
            posterPath = null,
            voteAverage = 9.0,
            releaseDate = "2008-07-18",
        ),
    )
}