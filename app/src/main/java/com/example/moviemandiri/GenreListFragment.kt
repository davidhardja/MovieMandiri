package com.example.moviemandiri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.moviemandiri.adapter.GenreAdapter
import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.domain.usecase.GenreUseCase
import com.example.moviemandiri.model.Genre
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GenreListFragment : Fragment() {

    @Inject
    lateinit var genreUseCase: GenreUseCase

    lateinit var homeScreenComponent: GenreComponent

    var adapter: GenreAdapter? = null

    var genreResult: ResultState<List<Genre>> = ResultState.Idle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeScreenComponent = MainApplication.appComponent.genreComponent().create()
        homeScreenComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                adapter = GenreAdapter(requireContext(), result.data)
                gridView.adapter = adapter
                gridView.setOnItemClickListener { _, _, i, _ ->
                    goToMovieListFragment(result.data[i].id)
                }
            }
            else -> {

            }
        }
    }

    private fun goToMovieListFragment(id: Int) {
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        // (activity as MainActivity).addMovieListFragment(id)
    }
}