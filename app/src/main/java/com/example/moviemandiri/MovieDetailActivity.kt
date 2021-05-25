package com.example.moviemandiri

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.injectDetailActivity(this)

        setContentView(R.layout.activity_movie_detail)
    }
}