package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lokal.jobapp.data.model.Location

class LocationConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromLocationList(value: List<Location>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toLocationList(value: String?): List<Location>? {
        val listType = object : TypeToken<List<Location>>() {}.type
        return gson.fromJson(value, listType)
    }
}
