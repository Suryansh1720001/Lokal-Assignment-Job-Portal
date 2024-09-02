package com.lokal.jobapp.di

import android.content.Context
import androidx.room.Room
import com.lokal.jobapp.data.local.AppDatabase
import com.lokal.jobapp.data.local.JobDao
import com.lokal.jobapp.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val BASE_URL = "https://testapi.getlokalapp.com/"

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "job_database").build()
    }

    @Provides
    fun provideJobDao(db: AppDatabase): JobDao {
        return db.jobDao()
    }
}
