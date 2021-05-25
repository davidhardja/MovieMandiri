package com.example.moviemandiri.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.moviemandiri.R
import com.example.moviemandiri.model.Genre
import kotlinx.android.synthetic.main.item_grid_genre.view.*

class GenreAdapter(private val context: Context, private val dataSet: List<Genre>) : BaseAdapter() {

    override fun getCount(): Int {
        return dataSet.size
    }

    override fun getItem(position: Int): Any {
        return dataSet[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val food = this.dataSet[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var foodView = inflator.inflate(R.layout.item_grid_genre, null)
        foodView.itemTextView.text = food.name!!

        return foodView
    }
}