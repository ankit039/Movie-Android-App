package com.example.practo_movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practo_movie.helpers.MovieListAdapter
import com.example.practo_movie.services.Api_Solver
import com.example.practo_movie.services.MovieList_Service
import com.example.practo_movie.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        loadMovies()
    }

    private fun loadMovies() {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(MovieList_Service::class.java)
        val requestCall = destinationService.getMovieList()
        //make network call
        requestCall.enqueue(object : Callback<Api_Solver> {

            override fun onResponse(
                call: Call<Api_Solver>,
                response: Response<Api_Solver>
            ) {
                //Log.d("Response","onResponse: ${response.body()?.get_api_solver()}")
                if (response.isSuccessful) {
                    val movieList = response.body()?.get_api_solver()!!
                    //Log.d("MovieList", "${ response.body()?.get_api_solver() }")
                    //Log.d("Response", "Movie List size: ${movieList.size}")
                    findViewById<RecyclerView>(R.id.movie_recycler).apply {
                        setHasFixedSize(true)

                        //making adapter here
                        var myadapter = MovieListAdapter(response.body()?.get_api_solver()!!)

                        //attaching adapter
                        layoutManager = GridLayoutManager(this@MovieActivity, 1)
                        adapter = myadapter

                        //implmented onClick event here
                        myadapter.setOnItemClickListener(object :
                            MovieListAdapter.onItemClickListener {
                            override fun onItemClick(`position`: Int) {

                                val list =movieList[position]

                                Log.d("Clicked Position- ", "$position")

                                //passing data to next MovieDetail screen to show more details
                                val intent=Intent(this@MovieActivity, MovieDetailActivity::class.java)
                                intent.putExtra("title",list.title)
                                intent.putExtra("desc",list.overview)
                                intent.putExtra("poster_path",list.poster_path)
                                intent.putExtra("backdrop_path",list.backdrop_path)
                                intent.putExtra("vote_avg",list.vote_average.toString())
                                startActivity(intent)
                            }
                        })

                    }
                } else {
                    Toast.makeText(
                        this@MovieActivity,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Api_Solver>, t: Throwable) {
                Log.e("Error", "$t")
                Toast.makeText(this@MovieActivity, "Something went wrong $t", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

}