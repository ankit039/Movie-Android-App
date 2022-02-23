package com.example.practo_movie.services

import com.example.practo_movie.models.MovieList_Model
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Api_Solver(api_solver: List<MovieList_Model>) {
    @SerializedName("results")
    @Expose
    private val Api_Solver: List<MovieList_Model> = api_solver
    fun get_api_solver(): List<MovieList_Model> {
        return Api_Solver
    }

}