package com.example.moviemandiri

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moviemandiri.adapter.GenreAdapter
import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.domain.usecase.GenreUseCase
import com.example.moviemandiri.model.Genre
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreActivity : AppCompatActivity() {

    @Inject
    lateinit var genreUseCase: GenreUseCase

    var adapter: GenreAdapter? = null

    var genreResult: ResultState<List<Genre>> = ResultState.Idle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainApplication.appComponent.injectMainActivity(this)

        setContentView(R.layout.activity_main)

        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = false
            fetchGenreList()
        }

        fetchGenreList()
    }

    private fun fetchGenreList() {
        GlobalScope.launch {
            genreResult = genreUseCase.getGenreList()
            withContext(Dispatchers.Main) {
                renderGridList()
            }
        }
    }

    private fun renderGridList() {
        when (val result = genreResult) {
            is ResultState.Success -> {
                adapter = GenreAdapter(this, result.data)
                gridView.adapter = adapter
                gridView.setOnItemClickListener { _, _, i, _ ->
                    goToMovieListFragment(result.data[i].id)
                }
                rlContainerEmpty.visibility = View.GONE
            }
            is ResultState.EmptySuccess -> {
                tvEmpty.text = getString(R.string.genre_not_found)
                rlContainerEmpty.visibility = View.VISIBLE
            }
            is ResultState.Error -> {
                tvEmpty.text = getString(R.string.genre_not_found)
                tvEmpty.text = getString(R.string.genre_not_found)
                rlContainerEmpty.visibility = View.VISIBLE
                Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToMovieListFragment(id: Int) {
        val intent = Intent(this, MovieListActivity::class.java).apply {
            putExtra("genreId", id)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}