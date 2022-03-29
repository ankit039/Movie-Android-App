package com.example.practo_movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practo_movie.models.MovieList
import com.example.practo_movie.viewModel.MovieActivityViewModel
import com.example.practo_movie.viewModel.MovieActivityViewModelFactory


class MovieActivity : AppCompatActivity() {

    lateinit var movieActivityViewModel: MovieActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        //initialize repository here
        val repository = (application as MovieListApplication).movieActivityRepository

        //initialize ViewModel here
        movieActivityViewModel =
            ViewModelProvider(this, MovieActivityViewModelFactory(repository)).get(
                MovieActivityViewModel::class.java
            )

        movieActivityViewModel.movies.observe(this, Observer {
            //Log.e("Data- ",it.results.toString())
            //setting data to RecyclerView
            findViewById<RecyclerView>(R.id.movie_recycler).apply {
                setHasFixedSize(true)
                setAdapterData(it.results)
            }
        })
    }

    fun setAdapterData(data: List<MovieList.Result>) {
        //adding adapter here
        val myadapter = MovieListAdapter(data)
        val movieRecycler = findViewById<RecyclerView>(R.id.movie_recycler)
        movieRecycler.layoutManager = GridLayoutManager(this@MovieActivity, 1)
        movieRecycler.adapter = myadapter

        //implemented onClick event here
        myadapter.setOnItemClickListener(object :
            MovieListAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val list = data[position]
                Log.d("Clicked Position- ", "$position")
                //passing data to next MovieDetail screen to show more details
                val intent = Intent(this@MovieActivity, MovieDetailActivity::class.java)
                intent.putExtra("title", list.title)
                intent.putExtra("desc", list.overview)
                intent.putExtra("poster_path", list.poster_path)
                intent.putExtra("backdrop_path", list.backdrop_path)
                intent.putExtra("vote_avg", list.vote_average.toString())
                startActivity(intent)
            }
        })
    }
}