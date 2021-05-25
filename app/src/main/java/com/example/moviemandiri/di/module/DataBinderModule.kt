package com.example.moviemandiri.di.module

import com.example.moviemandiri.repository.GenreRepository
import com.example.moviemandiri.repository.GenreRepositoryImpl
import com.example.moviemandiri.domain.usecase.GenreUseCase
import com.example.moviemandiri.domain.usecase.GenreUseCaseImpl
import com.example.moviemandiri.domain.usecase.MovieDetailUseCase
import com.example.moviemandiri.domain.usecase.MovieDetailUseCaseImpl
import com.example.moviemandiri.domain.usecase.MovieListUseCase
import com.example.moviemandiri.domain.usecase.MovieListUseCaseImpl
import com.example.moviemandiri.repository.MovieDetailRepository
import com.example.moviemandiri.repository.MovieDetailRepositoryImpl
import com.example.moviemandiri.repository.MovieListRepository
import com.example.moviemandiri.repository.MovieListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataBinderModule {

    @Binds
    abstract fun provideGenreRepository(repository: GenreRepositoryImpl) : GenreRepository

    @Binds
    abstract fun provideGenreUseCase(useCase: GenreUseCaseImpl) : GenreUseCase

    @Binds
    abstract fun provideMovieRepository(repository: MovieListRepositoryImpl) : MovieListRepository

    @Binds
    abstract fun provideMovieUseCase(useCase: MovieListUseCaseImpl) : MovieListUseCase

    @Binds
    abstract fun provideMovieDetailRepository(repository: MovieDetailRepositoryImpl) : MovieDetailRepository

    @Binds
    abstract fun provideMovieDetailUseCase(useCase: MovieDetailUseCaseImpl) : MovieDetailUseCase
}