package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lokal.jobapp.data.model.V3

class V3Converter {
    private val gson = Gson()

    @TypeConverter
    fun fromV3List(value: List<V3>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toV3List(value: String?): List<V3>? {
        val listType = object : TypeToken<List<V3>>() {}.type
        return gson.fromJson(value, listType)
    }
}
