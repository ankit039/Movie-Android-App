package com.example.practo_movie.rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.practo_movie.models.Converters
import com.example.practo_movie.models.MovieListModel

@Database(entities = [MovieListModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movielistDao(): MovielistDao

    companion object{
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}