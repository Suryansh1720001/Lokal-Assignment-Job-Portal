package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AnyConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromAny(value: Any?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAny(value: String?): Any? {
        val anyType = object : TypeToken<Any>() {}.type
        return gson.fromJson(value, anyType)
    }
}
