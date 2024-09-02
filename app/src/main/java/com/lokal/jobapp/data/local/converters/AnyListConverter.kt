package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AnyListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromAnyList(value: List<Any>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAnyList(value: String?): List<Any>? {
        val listType = object : TypeToken<List<Any>>() {}.type
        return gson.fromJson(value, listType)
    }
}
