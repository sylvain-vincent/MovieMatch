package com.qwin.moviematch

import android.os.Bundle
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.qwin.moviematch.feature.movies.presentation.detail.MovieDetailScreen
import com.qwin.moviematch.feature.movies.presentation.list.MovieListScreen
import com.qwin.moviematch.ui.theme.MovieMatchTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieMatchTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation()
                }
            }
        }
    }
}

sealed interface AppDestination {
    data object MovieList: AppDestination
    data class MovieDetails(val movieId: Int): AppDestination
}

@Composable
private fun AppNavigation() {
    val backStack = remember { mutableStateListOf<AppDestination>(AppDestination.MovieList) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { destination ->
            when(destination) {
                is AppDestination.MovieDetails -> NavEntry(destination) {
                    MovieDetailScreen(destination.movieId)
                }
                AppDestination.MovieList -> NavEntry(destination) {
                    MovieListScreen(onMovieClick = { id ->
                        backStack.add(AppDestination.MovieDetails(id))
                    })
                }
            }
        }
    )
}