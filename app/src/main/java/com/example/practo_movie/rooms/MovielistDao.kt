package com.example.practo_movie.rooms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practo_movie.models.MovieList

@Dao
interface MovielistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movieListModel: List<MovieList.Result>)

    @Query("SELECT COUNT(*) FROM movie_list_table")
    fun getCount(): Int

    @Query("SELECT * FROM movie_list_table")
    fun getAllMovies(): List<MovieList.Result>

}
