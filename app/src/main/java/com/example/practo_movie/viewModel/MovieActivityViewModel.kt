package com.example.practo_movie.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practo_movie.models.MovieList
import com.example.practo_movie.models.MovieListModel
import com.example.practo_movie.repository.MovieActivityRepository
import com.example.practo_movie.services.ApiSolver
import com.example.practo_movie.services.MovieListService
import com.example.practo_movie.services.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MovieActivityViewModel(private val repository: MovieActivityRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getMovies(1)
        }
    }

    val movies : LiveData<MovieList>
    get() = repository.movies

//    var movieList: List<MovieListModel> = ArrayList<MovieListModel>();
//
//    fun loadMovies() {
//        //movieList = ArrayList()
//        //initiate the service
//        val destinationService = ServiceBuilder.buildService(MovieListService::class.java)
//        val requestCall = destinationService.getMovieList()
//        //make network call
//        requestCall.enqueue(object : Callback<ApiSolver> {
//            override fun onResponse(
//                call: Call<ApiSolver>,
//                response: Response<ApiSolver>
//            ) {
//                if (response.isSuccessful) {
//                    Log.e("-5-5-5-", response.body()?.get_api_solver().toString()!!)
//                    movieList = response.body()?.get_api_solver()!!
//                } else {
//                    Log.e("Something went wrong- ", response.message())
//                }
//
//            }
//
//            //if network call is failed
//            override fun onFailure(call: Call<ApiSolver>, t: Throwable) {
//                Log.e("Error", "$t")
//            }
//
//        })
//    }
}

