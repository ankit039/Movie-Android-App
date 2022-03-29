package com.example.practo_movie.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practo_movie.models.MovieList
import com.example.practo_movie.repository.MovieActivityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

