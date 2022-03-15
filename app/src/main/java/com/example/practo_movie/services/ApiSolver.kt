package com.example.practo_movie.services

import com.example.practo_movie.models.MovieListModel
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ApiSolver(api_solver: List<MovieListModel>) {
    @SerializedName("results")
    @Expose
    private val ApiSolver: List<MovieListModel> = api_solver
    fun get_api_solver(): List<MovieListModel> {
        return ApiSolver
    }

}