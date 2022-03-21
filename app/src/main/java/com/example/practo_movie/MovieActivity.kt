package com.example.practo_movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practo_movie.helpers.MovieListAdapter
import com.example.practo_movie.services.ApiSolver
import com.example.practo_movie.services.MovieListService
import com.example.practo_movie.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.example.practo_movie.models.MovieListModel
import com.example.practo_movie.repository.MovieActivityRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.practo_movie.rooms.MovieDatabase.Companion.getDatabase
import com.example.practo_movie.viewModel.MovieActivityViewModel
import com.example.practo_movie.viewModel.MovieActivityViewModelFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit


class MovieActivity : AppCompatActivity() {

    lateinit var movieActivityViewModel: MovieActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val movieListService = ServiceBuilder.getInstance().create(MovieListService::class.java)
        val repository = MovieActivityRepository(movieListService)

        movieActivityViewModel = ViewModelProvider(this,MovieActivityViewModelFactory(repository)).get(MovieActivityViewModel::class.java)
        movieActivityViewModel.movies.observe(this, Observer {
            Log.e("%%%%",it.results.toString())
        })
//        myData()
//        setData()
//        loadMovies()
    }

    // To insert Data into Room
    fun insertData(model: List<MovieListModel>) {
        GlobalScope.launch {
            getDatabase(applicationContext).movielistDao().addMovies(model)
        }
    }

    // To Fetch Data from Room
    fun getData() {
        GlobalScope.launch {
            getDatabase(applicationContext).movielistDao().getAllMovies().collect {
                Log.e("Movie List- ", it.toString())
            }
        }
    }

    // To Get Count of all data Present
    suspend fun getCount(): Int = runBlocking {
        var result = async { getDatabase(applicationContext).movielistDao().getCount() }
        return@runBlocking result.await()
    }

//    fun myData(){
//        Log.e("%%%%%%%%%",movieActivityViewModel.movieList.toString())
//    }
//
//    fun setData( ){
//        movieActivityViewModel.loadMovies()
//        myData()
//    }

    /*
    private fun loadMovies() {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(MovieListService::class.java)
        val requestCall = destinationService.getMovieList()
        //make network call
        requestCall.enqueue(object : Callback<ApiSolver> {

            override fun onResponse(
                call: Call<ApiSolver>,
                response: Response<ApiSolver>
            ) {
                //Log.d("Response","onResponse: ${response.body()?.get_api_solver()}")
                if (response.isSuccessful) {
                    val movieList = response.body()?.get_api_solver()!!
                    //Log.d("MovieList", "${ response.body()?.get_api_solver() }")
                    //Log.d("Response", "Movie List size: ${movieList.size}")

                    insertData(movieList)
                    getData()

                    GlobalScope.launch {
                        Log.e("Total Count- ", getCount().toString())
                    }

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

                                val list = movieList[position]

                                Log.d("Clicked Position- ", "$position")

                                //passing data to next MovieDetail screen to show more details
                                val intent =
                                    Intent(this@MovieActivity, MovieDetailActivity::class.java)
                                intent.putExtra("title", list.title)
                                intent.putExtra("desc", list.overview)
                                intent.putExtra("poster_path", list.poster_path)
                                intent.putExtra("backdrop_path", list.backdrop_path)
                                intent.putExtra("vote_avg", list.vote_average.toString())
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

            override fun onFailure(call: Call<ApiSolver>, t: Throwable) {
                Log.e("Error", "$t")
                Toast.makeText(this@MovieActivity, "Something went wrong $t", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }
    */

}