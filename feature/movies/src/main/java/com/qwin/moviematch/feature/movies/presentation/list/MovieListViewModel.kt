package com.qwin.moviematch.feature.movies.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwin.moviematch.feature.movies.domain.model.Movie
import com.qwin.moviematch.feature.movies.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {

    val uiState: StateFlow<MovieListUiState> = movieRepository
        .getMovies()
        .map<List<Movie>, MovieListUiState> { movies -> MovieListUiState.Success(movies) }
        .catch { throwable ->
            emit(MovieListUiState.Error(throwable.message ?: "Unknown error"))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieListUiState.Loading
        )

}