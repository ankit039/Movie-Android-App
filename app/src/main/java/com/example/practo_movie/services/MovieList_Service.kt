package com.example.practo_movie.services

import com.example.practo_movie.models.MovieList_Model
import retrofit2.Call
import retrofit2.http.GET

interface MovieList_Service {
    @GET("popular?api_key=2e365ca052181a25b546c4372acfa99b")
    fun getMovieList(): Call<Api_Solver>
}