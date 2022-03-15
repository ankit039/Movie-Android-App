package com.example.practo_movie.rooms

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.practo_movie.models.MovieListModel

@Dao
interface MovielistDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movieListModel: MovieListModel)

    @Query("SELECT * FROM movie_list_table ORDER BY ID ASC")
    fun readAllData(): LiveData<List<MovieListModel>>
}
