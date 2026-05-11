package com.qwin.moviematch.feature.movies.domain.repository

import com.qwin.moviematch.feature.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    suspend fun getMovieById(id: Int): Movie?
}