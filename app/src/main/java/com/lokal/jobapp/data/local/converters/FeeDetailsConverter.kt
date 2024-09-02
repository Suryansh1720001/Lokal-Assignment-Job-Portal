package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.lokal.jobapp.data.model.FeeDetails

class FeeDetailsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromFeeDetails(value: FeeDetails?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toFeeDetails(value: String?): FeeDetails? {
        return gson.fromJson(value, FeeDetails::class.java)
    }
}
