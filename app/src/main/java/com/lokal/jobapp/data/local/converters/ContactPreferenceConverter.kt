package com.lokal.jobapp.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.lokal.jobapp.data.model.ContactPreference

class ContactPreferenceConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromContactPreference(value: ContactPreference?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toContactPreference(value: String?): ContactPreference? {
        return gson.fromJson(value, ContactPreference::class.java)
    }
}
