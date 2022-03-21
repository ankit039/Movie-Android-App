package com.example.practo_movie.services

import com.example.practo_movie.models.MovieList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListService {

    @GET("popular")
    suspend fun getMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieList>
}