package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lokal.jobapp.data.model.JobTag

class JobTagConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromJobTagList(value: List<JobTag>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toJobTagList(value: String?): List<JobTag>? {
        val listType = object : TypeToken<List<JobTag>>() {}.type
        return gson.fromJson(value, listType)
    }
}
