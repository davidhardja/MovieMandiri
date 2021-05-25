package com.example.moviemandiri.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemandiri.MovieDetailActivity
import com.example.moviemandiri.MovieListActivity
import com.example.moviemandiri.R
import com.example.moviemandiri.model.Movie
import com.example.moviemandiri.network.Api

class MovieAdapter(var dataSet: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            textView = view.findViewById(R.id.itemListTextView)
            imageView = view.findViewById(R.id.itemListImageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_movie, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie = dataSet[position]
        viewHolder.textView.text = movie.title
        movie.posterPath?.let {
            Glide.with(viewHolder.imageView.context)
                .load(Api.getPosterPath(it))
                .into(viewHolder.imageView)
        }
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(it.context, MovieDetailActivity::class.java)
            intent.putExtra("movieId", movie.id)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount() = dataSet.size
}

