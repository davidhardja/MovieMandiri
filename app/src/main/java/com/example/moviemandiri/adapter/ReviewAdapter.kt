package com.example.moviemandiri.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemandiri.R
import com.example.moviemandiri.model.Review
import com.example.moviemandiri.network.Api

class ReviewAdapter(var dataSet: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        val tvReview: TextView = view.findViewById(R.id.tvReview)
        val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_review, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val review = dataSet[position]
        viewHolder.tvUsername.text = review.authorDetail.username
        viewHolder.tvReview.text = review.content
        review.authorDetail.avatarPath?.let {
            Glide.with(viewHolder.ivAvatar.context)
                .load(Api.getPosterPath(it))
                .into(viewHolder.ivAvatar)
        }
    }

    override fun getItemCount() = dataSet.size
}