package com.example.practo_movie.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practo_movie.repository.MovieActivityRepository


class MovieActivityViewModelFactory(private val repository: MovieActivityRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieActivityViewModel(repository) as T
    }
}