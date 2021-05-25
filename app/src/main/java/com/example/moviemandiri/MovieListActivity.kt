package com.example.moviemandiri

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemandiri.adapter.MovieAdapter
import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.domain.usecase.MovieListUseCase
import com.example.moviemandiri.model.Movie
import kotlinx.android.synthetic.main.activity_discover.*
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var movieListUseCase: MovieListUseCase

    var movieResult: ResultState<List<Movie>> = ResultState.Idle()

    var movieList: ArrayList<Movie> = ArrayList()

    var genreId = 0

    var page = 1

    var adapter: MovieAdapter = MovieAdapter(ArrayList())

    var layoutManager: LinearLayoutManager = LinearLayoutManager(this)

    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.injectDiscoverActivity(this)

        setContentView(R.layout.activity_discover)
        genreId = intent.getIntExtra("genreId", 0)
        page = 1

        initRecyclerView()

        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = false
            page = 1
            movieList.clear()
            fetchMovieList()
        }

        fetchMovieList()
    }

    private fun fetchMovieList() {
        GlobalScope.launch {
            movieResult = movieListUseCase.getMovieList(genreId, page)
            isLoading = false
            withContext(Dispatchers.Main) {
                renderMovieList()
            }
        }
    }

    private fun initRecyclerView() {
        rvMovieList.adapter = adapter
        rvMovieList.layoutManager = layoutManager
        rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading) {

                    if (layoutManager.findLastCompletelyVisibleItemPosition() == movieList.size - 1) {
                        isLoading = true
                        fetchMovieList()
                    }
                }
            }
        })
    }

    private fun renderMovieList() {
        when (val result = movieResult) {
            is ResultState.Success -> {
                if (page == 1) {
                    movieList.clear()
                }
                movieList.addAll(result.data)
                adapter.dataSet = movieList
                page++
                adapter.notifyDataSetChanged()
            }
            is ResultState.Error -> {
                Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
            }
        }

        if (movieList.isEmpty()) {
            tvEmpty.text = getString(R.string.movie_not_found)
            rlContainerEmpty.visibility = View.VISIBLE
        } else {
            rlContainerEmpty.visibility = View.GONE
        }
    }
}