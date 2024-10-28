package com.example.starwarsdocs.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CustomTypeConverter {
    @TypeConverter
    fun fromFilmList(films: List<String>?): String {
        return Gson().toJson(films)
    }

    @TypeConverter
    fun toFilmList(filmString: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(filmString, listType)
    }
}