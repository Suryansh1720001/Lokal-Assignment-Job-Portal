package com.lokal.jobapp.data.remote

import com.lokal.jobapp.data.model.JobListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("common/jobs")
    suspend fun getJobs(
        @Query("page") page: Int
    ): JobListResponse
}
