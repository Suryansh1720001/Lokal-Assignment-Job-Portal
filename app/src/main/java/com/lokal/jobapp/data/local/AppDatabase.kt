package com.lokal.jobapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lokal.jobapp.data.local.converters.AnyConverter
import com.lokal.jobapp.data.local.converters.AnyListConverter
import com.lokal.jobapp.data.local.converters.ContactPreferenceConverter
import com.lokal.jobapp.data.local.converters.ContentV3Converter
import com.lokal.jobapp.data.local.converters.CreativeConverter
import com.lokal.jobapp.data.local.converters.FeeDetailsConverter
import com.lokal.jobapp.data.local.converters.JobTagConverter
import com.lokal.jobapp.data.local.converters.LocationConverter
import com.lokal.jobapp.data.local.converters.PrimaryDetailsConverter
import com.lokal.jobapp.data.local.converters.TranslatedContentConverter
import com.lokal.jobapp.data.local.converters.V3Converter
import com.lokal.jobapp.data.model.JobResult

@Database(
    entities = [JobResult::class], // Add all entities here
    version = 1,             // Increment the version if you change the schema
    exportSchema = false     // Disable exporting schema for simplicity
)
@TypeConverters(
    V3Converter::class,
    CreativeConverter::class,
    FeeDetailsConverter::class,
    JobTagConverter::class,
    LocationConverter::class,
    AnyListConverter::class,
    ContactPreferenceConverter::class,
    ContentV3Converter::class,
    AnyConverter::class,
    PrimaryDetailsConverter::class,
    TranslatedContentConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    // Define DAOs to be accessible through the database
    abstract fun jobDao(): JobDao
}