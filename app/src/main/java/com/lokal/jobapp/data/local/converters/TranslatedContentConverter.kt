package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.lokal.jobapp.data.model.TranslatedContent

class TranslatedContentConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromTranslatedContent(value: TranslatedContent?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toTranslatedContent(value: String?): TranslatedContent? {
        return gson.fromJson(value, TranslatedContent::class.java)
    }
}
