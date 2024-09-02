package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lokal.jobapp.data.model.Creative

class CreativeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCreativeList(value: List<Creative>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCreativeList(value: String?): List<Creative>? {
        val listType = object : TypeToken<List<Creative>>() {}.type
        return gson.fromJson(value, listType)
    }
}
