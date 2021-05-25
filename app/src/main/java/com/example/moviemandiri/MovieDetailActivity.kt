package com.example.moviemandiri

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.domain.usecase.MovieDetailUseCase
import com.example.moviemandiri.model.Movie
import com.example.moviemandiri.model.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var movieDetailUseCase: MovieDetailUseCase

    var movieResult: ResultState<MovieDetail> = ResultState.Idle()

    var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.injectDetailActivity(this)

        setContentView(R.layout.activity_movie_detail)
        movieId = intent.getIntExtra("movieId", 0)

        fetchMovieList()
    }

    private fun fetchMovieList() {
        GlobalScope.launch {
            movieResult = movieDetailUseCase.getMovieDetail(movieId)
            withContext(Dispatchers.Main) {

            }
        }
    }
}