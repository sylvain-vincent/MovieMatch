package com.qwin.moviematch.feature.movies.di

import com.qwin.moviematch.feature.movies.data.FakeMovieRepository
import com.qwin.moviematch.feature.movies.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(fakeMovieRepository: FakeMovieRepository): MovieRepository
}