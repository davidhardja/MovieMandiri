package com.example.moviemandiri.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemandiri.R
import com.example.moviemandiri.model.Video
import com.example.moviemandiri.network.Api

class VideoAdapter(var dataSet: List<Video>) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.itemListImageView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_video, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val video = dataSet[position]

        Glide.with(viewHolder.imageView.context)
            .load(Api.getYoutubeThumbnailPath(video.key))
            .into(viewHolder.imageView)

        viewHolder.itemView.setOnClickListener {
            it.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(Api.getYoutubeVideoPath(video.key))))
        }
    }

    override fun getItemCount() = dataSet.size
}