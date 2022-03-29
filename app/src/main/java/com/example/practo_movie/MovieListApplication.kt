package com.example.practo_movie

import android.app.Application
import com.example.practo_movie.repository.MovieActivityRepository
import com.example.practo_movie.rooms.MovieDatabase
import com.example.practo_movie.services.MovieListService
import com.example.practo_movie.services.ServiceBuilder

class MovieListApplication : Application() {

    lateinit var movieActivityRepository: MovieActivityRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        //MMVM Retrofit & Service Usage
        val movieListService = ServiceBuilder.getInstance().create(MovieListService::class.java)
        val database = MovieDatabase.getDatabase(applicationContext)
        movieActivityRepository = MovieActivityRepository(movieListService, database)
    }
}