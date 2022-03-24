package com.example.practo_movie.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practo_movie.models.MovieList
import com.example.practo_movie.services.ApiSolver
import com.example.practo_movie.services.MovieListService

class MovieActivityRepository(private val movieListService: MovieListService) {

    private val movieLiveData = MutableLiveData<MovieList>()

    val movies: LiveData<MovieList>
        get() = movieLiveData

    suspend fun getMovies(page: Int) {
        val result = movieListService.getMovies(
            api_key = "2e365ca052181a25b546c4372acfa99b",
            language = "en-US",
            page=page
        )
        val response = result?.execute()
        if (response != null) {
            movieLiveData.postValue(response.body())
        }else{
            Log.e("Error- ","got error in MovieActivityRepository")
        }
    }
}