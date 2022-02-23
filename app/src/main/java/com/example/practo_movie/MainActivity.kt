package com.example.practo_movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //navigate to movie_recyclerview Activity
        val btn_movie_scrollview = findViewById<Button>(R.id.btn_movie_scrollview)
        btn_movie_scrollview.setOnClickListener(
            View.OnClickListener { startActivity(Intent(this, MovieActivity::class.java)) }
        )
    }
}