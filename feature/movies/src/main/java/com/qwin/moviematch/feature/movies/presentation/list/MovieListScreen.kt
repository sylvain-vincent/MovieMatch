package com.qwin.moviematch.feature.movies.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.qwin.moviematch.feature.movies.domain.model.Movie

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    onMovieClick: (id: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        MovieListContent(uiState, onMovieClick, innerPadding)
    }
}

@Composable
fun MovieListContent(
    uiState: MovieListUiState,
    onMovieClick: (Int) -> Unit,
    contentPadding: PaddingValues = PaddingValues(),
) {
    when (uiState) {
        is MovieListUiState.Error -> MovieListError(uiState.message, contentPadding)
        MovieListUiState.Loading -> MovieListLoader(contentPadding)
        is MovieListUiState.Success -> MovieList(
            movieList = uiState.movies,
            contentPadding,
            onMovieClick
        )
    }
}

@Composable
fun MovieList(
    movieList: List<Movie>,
    contentPadding: PaddingValues = PaddingValues(),
    onMovieClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding() + 16.dp,
            bottom = contentPadding.calculateBottomPadding() + 16.dp,
            start = 16.dp,
            end = 16.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movieList, key = { movie -> movie.id }) { movie ->
            MovieCard(movie, onMovieClick)
        }
    }
}

@Composable
fun MovieCard(movie: Movie, onMovieClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMovieClick.invoke(movie.id) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
            )
        }
    }
}

@Composable
fun MovieListLoader(contentPadding: PaddingValues = PaddingValues()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun MovieListError(message: String, contentPadding: PaddingValues = PaddingValues()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = message)
    }
}

@Preview
@Composable
fun MovieListPreview() {
    val fakeList = listOf(
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
        )
    )
    MovieList(fakeList) {}
}