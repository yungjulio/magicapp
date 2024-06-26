package com.example.magicapp.data.local

import androidx.room.TypeConverter
import com.example.magicapp.data.remote.Legality
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromLegalityList(legality: List<Legality>?): String? {
        if (legality == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Legality>>() {}.type
        return gson.toJson(legality, type)
    }

    @TypeConverter
    fun toLegalityList(legalityString: String?): List<Legality>? {
        if (legalityString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Legality>>() {}.type
        return gson.fromJson(legalityString, type)
    }
}
