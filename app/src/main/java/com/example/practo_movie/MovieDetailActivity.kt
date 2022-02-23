package com.example.practo_movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        Picasso.get()
            .load("https://www.themoviedb.org/t/p/w1066_and_h600_bestv2" + intent.getStringExtra("backdrop_path"))
            .into(findViewById<ImageView>(R.id.movieBack))
        Picasso.get()
            .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2" + intent.getStringExtra("poster_path"))
            .into(findViewById<ImageView>(R.id.movieFront))
        findViewById<TextView>(R.id.movieTitle).text = intent.getStringExtra("title")
        findViewById<TextView>(R.id.movieAvg).text = "(IMDb Rate- " + intent.getStringExtra("vote_avg") + ")"
        findViewById<TextView>(R.id.movieDesc).text = intent.getStringExtra("desc")
    }
}