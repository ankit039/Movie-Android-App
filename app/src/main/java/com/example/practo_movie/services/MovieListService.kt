package com.example.practo_movie.services

import retrofit2.Call
import retrofit2.http.GET

interface MovieListService {
    @GET("popular?api_key=2e365ca052181a25b546c4372acfa99b")
    fun getMovieList(): Call<ApiSolver>
}