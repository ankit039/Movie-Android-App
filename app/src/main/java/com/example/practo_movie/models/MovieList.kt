package com.example.practo_movie.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

data class MovieList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) {
    @Entity(tableName = "movie_list_table")
    data class Result(
        //@PrimaryKey(autoGenerate = true) val srno: Int,
        @PrimaryKey @NonNull val id: Int,
        @NonNull val adult: Boolean,
        @NonNull val backdrop_path: String,
        @NonNull val genre_ids: List<Int>,
        @NonNull val original_language: String,
        @NonNull val original_title: String,
        @NonNull val overview: String,
        @NonNull val popularity: Double,
        @NonNull val poster_path: String,
        @NonNull val release_date: String,
        @NonNull val title: String,
        @NonNull val video: Boolean,
        @NonNull val vote_average: Double,
        @NonNull val vote_count: Int
    )
}