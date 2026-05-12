package com.qwin.moviematch.feature.movies.presentation.list

import com.qwin.moviematch.feature.movies.domain.model.Movie


sealed interface MovieListUiState {
    data object Loading: MovieListUiState
    data class Success(val movies: List<Movie>): MovieListUiState
    data class Error(val message: String): MovieListUiState
}