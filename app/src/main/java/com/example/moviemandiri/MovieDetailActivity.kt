package com.example.moviemandiri

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviemandiri.adapter.ReviewAdapter
import com.example.moviemandiri.adapter.VideoAdapter
import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.domain.usecase.MovieDetailUseCase
import com.example.moviemandiri.domain.usecase.ReviewUseCase
import com.example.moviemandiri.domain.usecase.VideoUseCase
import com.example.moviemandiri.model.MovieDetail
import com.example.moviemandiri.model.Review
import com.example.moviemandiri.model.Video
import com.example.moviemandiri.network.Api
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var movieDetailUseCase: MovieDetailUseCase

    @Inject
    lateinit var videoUseCase: VideoUseCase

    @Inject
    lateinit var reviewUseCase: ReviewUseCase

    var movieResult: ResultState<MovieDetail> = ResultState.Idle()

    var videoResult: ResultState<List<Video>> = ResultState.Idle()

    var reviewResult: ResultState<List<Review>> = ResultState.Idle()

    var movieId = 0

    var page = 1

    var adapter: VideoAdapter = VideoAdapter(ArrayList())

    var layoutManager: LinearLayoutManager = LinearLayoutManager(this).apply {
        orientation = LinearLayoutManager.HORIZONTAL
    }

    var isLoading = false

    var reviewList: ArrayList<Review> = ArrayList()

    var reviewAdapter: ReviewAdapter = ReviewAdapter(reviewList)

    var reviewLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.injectDetailActivity(this)

        setContentView(R.layout.activity_movie_detail)
        movieId = intent.getIntExtra("movieId", 0)
        initList()
        fetchData()
    }

    private fun initList() {
        svContainer.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (v?.getChildAt(v.childCount - 1) != null) {
                if ((scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)) &&
                    scrollY > oldScrollY
                ) {
                    if (!isLoading) {
                        if (reviewLayoutManager.findLastCompletelyVisibleItemPosition() == reviewList.size - 1) {
                            isLoading = true
                            GlobalScope.launch {
                                fetchReview()
                                withContext(Dispatchers.Main) {
                                    isLoading = false
                                    renderMovieDetail()
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    private fun fetchData() {
        GlobalScope.launch {
            fetchMovieList()
            fetchVideo()

            withContext(Dispatchers.Main) {
                renderMovieDetail()
            }
        }
    }

    private suspend fun fetchMovieList() {
        movieResult = movieDetailUseCase.getMovieDetail(movieId)
    }

    private suspend fun fetchVideo() {
        videoResult = videoUseCase.getVideo(movieId)
    }

    private suspend fun fetchReview() {
        reviewResult = reviewUseCase.getReview(movieId, page)
    }

    private fun renderMovieDetail() {
        when (val result = movieResult) {
            is ResultState.Success -> {
                renderHeader(result.data)
            }
            is ResultState.Error -> {
                Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
            }
        }

        when (val result = videoResult) {
            is ResultState.Success -> {
                renderVideo(result.data)
            }
        }

        when (val result = reviewResult) {
            is ResultState.Success -> {
                renderReview(result.data)
                if (result.data.isNotEmpty()) {
                    page++
                }
            }
        }
    }

    private fun renderVideo(videoList: List<Video>) {
        if (videoList.isNotEmpty()) {
            adapter.dataSet = videoList
            rvTrailer.adapter = adapter
            rvTrailer.layoutManager = layoutManager
            llContainerTrailer.visibility = View.VISIBLE
        } else {
            llContainerTrailer.visibility = View.GONE
        }
    }

    private fun renderReview(list: List<Review>) {
        if (page == 1) {
            reviewList.clear()
        }
        if (list.isNotEmpty()) {
            reviewList.addAll(list)
        }

        rvReviews.adapter = reviewAdapter
        rvReviews.layoutManager = reviewLayoutManager

        if (reviewList.isNotEmpty()) {
            llContainerReviews.visibility = View.VISIBLE
        } else {
            llContainerReviews.visibility = View.GONE
        }
    }

    private fun renderHeader(movieDetail: MovieDetail) {
        tvTitle.text = movieDetail.title
        tvTagline.text = movieDetail.tagline
        tvRelease.text = getString(R.string.release_data, movieDetail.releaseDate)
        rbRating.rating = movieDetail.voteAverage.toFloat() / 2
        tvSummary.text = movieDetail.overview
        movieDetail.posterPath?.let {
            Glide.with(this)
                .load(Api.getPosterPath(it))
                .into(ivPoster)
        }
    }
}