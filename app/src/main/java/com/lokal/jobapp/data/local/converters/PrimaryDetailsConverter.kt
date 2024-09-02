package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.lokal.jobapp.data.model.PrimaryDetails

class PrimaryDetailsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromPrimaryDetails(value: PrimaryDetails?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPrimaryDetails(value: String?): PrimaryDetails? {
        return gson.fromJson(value, PrimaryDetails::class.java)
    }
}
