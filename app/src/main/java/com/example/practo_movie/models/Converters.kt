package com.example.practo_movie.models

import androidx.room.TypeConverter
import androidx.room.TypeConverters

class Converters {
    @TypeConverter
    fun fromListIntToString(value: List<Int>?) : String? {
        return value?.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromStringToListInt(value: String?) : List<Int>? {
        return value?.split(",")?.map { it.trim().toInt() }
    }

}