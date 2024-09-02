package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.lokal.jobapp.data.model.ContentV3

class ContentV3Converter {
    private val gson = Gson()

    @TypeConverter
    fun fromContentV3(value: ContentV3?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toContentV3(value: String?): ContentV3? {
        return gson.fromJson(value, ContentV3::class.java)
    }
}
